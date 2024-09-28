package com.hpbt.userservice.services;

import com.hpbt.userservice.entities.User;
import com.hpbt.userservice.security.CustomUserDetails;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    @Value("${jwt.signerKey}")
    private String jwtSecret;

    public String generateJwtToken(User user) throws JOSEException {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issueTime(new Date(
                        Instant.now().toEpochMilli()
                ))
                .expirationTime(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .claim("username", user.getUsername())
                .build();

//        .expirationTime(new Date(
//                Instant.now().plus(2, ChronoUnit.HOURS).toEpochMilli()
//        ))
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).build();

        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        JWSSigner signer = new MACSigner(jwtSecret.getBytes());
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public Boolean validateJwtToken(String token, CustomUserDetails customUserDetails) throws ParseException, JOSEException {
        String extractUsername = extractUsername(token);
        return (extractUsername.equals(customUserDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) throws ParseException, JOSEException{
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(jwtSecret);

        if(signedJWT.verify(verifier)){
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
            Date expirationTime = claimsSet.getExpirationTime();
            return expirationTime == null || expirationTime.before(new Date());
        }

        return false;
    }

    public String extractUsername(String token) throws ParseException {
        log.info(token);
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
        return claimsSet.getStringClaim("username");
    }



}
