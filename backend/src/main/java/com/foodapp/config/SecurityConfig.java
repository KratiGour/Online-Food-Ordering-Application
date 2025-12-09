package com.foodapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SECURITY CONFIGURATION
 * 
 * This class configures security-related beans.
 * 
 * @Configuration tells Spring this class contains bean definitions
 */
@Configuration
public class SecurityConfig {
    
    /**
     * PASSWORD ENCODER BEAN
     * 
     * BCrypt is a password hashing algorithm.
     * 
     * WHY USE IT?
     * - Never store plain passwords in database!
     * - BCrypt is one-way encryption (can't be decrypted)
     * - Even if database is hacked, passwords are safe
     * 
     * HOW IT WORKS:
     * Plain password: "password123"
     * Encrypted: "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy"
     * 
     * Same password encrypted twice gives different results!
     * This prevents rainbow table attacks.
     * 
     * @Bean tells Spring to create and manage this object
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * SECURITY FILTER CHAIN
     * 
     * Temporarily disable security to test APIs easily.
     * Later we'll add JWT authentication.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
