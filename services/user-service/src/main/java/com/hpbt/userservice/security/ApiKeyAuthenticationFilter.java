package com.hpbt.userservice.security;

import com.hpbt.userservice.services.AccessKeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {
    AccessKeyService accessKeyService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        String apiKey = request.getHeader("X-API-KEY");
        System.out.println(apiKey);

        if(apiKey != null) {
            System.out.println(accessKeyService.findAccessKeyByApiKey(apiKey));
            if(accessKeyService.findAccessKeyByApiKey(apiKey) != null){
                if(accessKeyService.validateAccessKey(apiKey)){
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(apiKey, null, null);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println(SecurityContextHolder.getContext().getAuthentication());
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
