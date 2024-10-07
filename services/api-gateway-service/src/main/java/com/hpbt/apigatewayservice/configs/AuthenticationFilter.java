package com.hpbt.apigatewayservice.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpbt.apigatewayservice.Utils.Commons.StatusCode;
import com.hpbt.apigatewayservice.dto.responses.ApiResponse;
import com.hpbt.apigatewayservice.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class AuthenticationFilter implements GlobalFilter, Ordered {
    UserService userService;

    @Value("${api.prefix}")
    @NonFinal
    private String apiPrefix;

//    @Value("${api.public-endpoints}")
//    @NonFinal
    private String [] publicEndpoints = {"/user/.*", "/login"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if(isPublicEndpoint(exchange.getRequest())){
            return chain.filter(exchange);
        }

        List<String> authHeader = exchange.getRequest().getHeaders().get("X-API-KEY");
        if(CollectionUtils.isEmpty(authHeader)) {
            return unauthenticated(exchange.getResponse());
        }
        String apiKey = authHeader.get(0);
        return userService.validateApiKey(apiKey)
                .flatMap(response -> response.getResult().isValid() ? chain.filter(exchange) : unauthenticated(exchange.getResponse()))
                .onErrorResume(e -> {
                    log.error("Error during API key validation", e);
                    return unauthenticated(exchange.getResponse());
                });
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private boolean isPublicEndpoint(ServerHttpRequest request){
        return Arrays.stream(publicEndpoints)
                .anyMatch(s -> request.getURI().getPath().matches(apiPrefix + s));
    }

    Mono<Void> unauthenticated(ServerHttpResponse response){
        StatusCode statusCode = StatusCode.UNAUTHENTICATED;
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String body = null;
        try{
            body = new ObjectMapper().writeValueAsString(ApiResponse.builder()
                    .code(statusCode.getCode())
                    .message(statusCode.getMessage())
                    .build());
        } catch(JsonProcessingException ex){
            throw new RuntimeException(ex);
        }
        return response.writeWith(Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }
}
