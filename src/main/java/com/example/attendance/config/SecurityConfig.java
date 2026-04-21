package com.example.attendance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF
                .csrf(csrf -> csrf.disable())

                // Define which URLs are allowed
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/student", "/students").permitAll()
                        .anyRequest().authenticated()
                )

                // Enable default login page
                .formLogin(form -> form.disable());

        return http.build();
    }
}
