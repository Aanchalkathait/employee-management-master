package com.developer.employeemanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/actuator/health")).permitAll() // Public access to the health endpoint
                        .anyRequest().authenticated() // Secure all other endpoints
                )
                .httpBasic(Customizer.withDefaults()) // Enable basic authentication
                .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity (not recommended for production)

        return http.build();
    }
}
