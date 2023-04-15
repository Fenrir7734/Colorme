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
public class WebSecurityConfig {

    private final EntryPoint entryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                    .oauth2Login()
                    .defaultSuccessUrl("/")
                .and()
                    .authorizeHttpRequests()
                    .requestMatchers("/", "/**.html", "/**.js").permitAll()
                    .requestMatchers(permitAllEndpoints()).permitAll()
                    .requestMatchers("/api/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(entryPoint)
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .csrf().disable()
                    .cors().disable()
                    .build();
    }

    private AntPathRequestMatcher[] permitAllEndpoints() {
        return new AntPathRequestMatcher[] {
                new AntPathRequestMatcher("/api/v1/tags", "GET"),
                new AntPathRequestMatcher("/api/v1/palettes/{code}", "GET")
        };
    }
}
