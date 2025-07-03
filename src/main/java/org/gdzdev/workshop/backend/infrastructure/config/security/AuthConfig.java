package org.gdzdev.workshop.backend.infrastructure.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthConfig {
    private final SecurityFilter securityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.PUT, "/seeder/*").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/sales/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/purchase").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/categories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/purchase").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/categories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/purchase").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/categories").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

