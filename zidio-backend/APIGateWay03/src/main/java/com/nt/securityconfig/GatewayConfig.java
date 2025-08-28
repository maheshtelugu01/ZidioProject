package com.nt.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nt.jwt.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {
	@Autowired
    private  JwtAuthenticationFilter jwtFilter;

     
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("auth-service", r -> r.path("/auth/**")
                .uri("lb://AUTH-SERVICE"))
            .route("student-service", r -> r.path("/api/student/**")
                .filters(f -> f.filter(jwtFilter))
                .uri("lb://STUDENT-SERVICE"))
            .route("user-service", r -> r.path("/api/user/**")
                .filters(f -> f.filter(jwtFilter))
                .uri("lb://USER-SERVICE"))
            .build();
    }
}