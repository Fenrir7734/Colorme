package com.fenrir.colorme.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // Fix HttpSecurity autowire warning in Intellij
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.oauth2Login()
                .and()
                    .authorizeHttpRequests()
                    .requestMatchers("/", "/**.html", "/**.js").permitAll()
                    .requestMatchers("/api/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
                .and()
                    .csrf().disable()
                    .cors().disable()
                    .build();
    }
}
