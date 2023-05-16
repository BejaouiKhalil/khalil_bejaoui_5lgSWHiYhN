package com.example.khalil_bejaoui_5lgSWHiYhN.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests().requestMatchers("/api/**").authenticated() // Protect the /api/** endpoints
              .anyRequest().permitAll() // Allow access to other endpoints
              .and().httpBasic();
        return  http.build();
    }
}
