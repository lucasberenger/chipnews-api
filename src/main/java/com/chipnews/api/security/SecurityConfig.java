package com.chipnews.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// Desativa CSRF (para testes)
                .headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/**").permitAll() // Libera todas as rotas de usuário
                        .requestMatchers("/swagger-ui/**").permitAll() // Libera todas as rotas do Swagger
                        .requestMatchers("/h2-console/**").permitAll() // Libera todas as rotas do H2
                        .anyRequest().authenticated() // Bloqueia outras rotas
                );

        return http.build();
    }
}
