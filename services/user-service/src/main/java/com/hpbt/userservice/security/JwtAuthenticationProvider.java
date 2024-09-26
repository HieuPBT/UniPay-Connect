package com.hpbt.userservice.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtAuthenticationProvider {
    @Value("${jwt.secret")
    private static String jwtSecret;

//    public String generateToken(Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + 3600 * 1000);
//
//        return Jwts
//    }

    public static String generateToken(String username) throws JOSEException {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issueTime(new Date(
                        Instant.now().plus(2, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("userrname", "Custom")
                .build();

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).build();

        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        JWSSigner signer = new MACSigner(jwtSecret.getBytes());
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

}
