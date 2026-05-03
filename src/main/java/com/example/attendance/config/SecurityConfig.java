package com.example.attendance.config;

import com.example.attendance.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Inject CustomUserDetailsService
    private final CustomUserDetailsService customUserDetailsService;

    // Constructor injection
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    // Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // Authentication provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider(customUserDetailsService);

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    // Security configuration
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF
                .csrf(csrf -> csrf.disable())

                // URL access rules
                .authorizeHttpRequests(auth -> auth

                        // Public pages
                        .requestMatchers("/login").permitAll()

                        // Admin only
                        .requestMatchers(
                                "/mark-attendance",
                                "/students/delete/*",
                                "/students/edit/*",
                                "/students/update/*"
                        ).hasRole("ADMIN")

                        // Admin + Student
                        .requestMatchers(
                                "/students-view",
                                "/attendance-summary/**"
                        ).hasAnyRole("ADMIN", "STUDENT")

                        // Everything else
                        .anyRequest().permitAll()
                )

                // Enable login page
                .formLogin(form -> form
                        .defaultSuccessUrl("/students-view", true)
                );

        return http.build();
    }
}