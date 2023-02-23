package com.bookers.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
//                .cors().configurationSource(new CorsConfigurationSource() {
//                    @Override
//                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                        CorsConfiguration config = new CorsConfiguration();
//
//                        config.setAllowedOrigins(Collections.singletonList("*"));
//                        config.setAllowedMethods(Collections.singletonList("*"));
//                        config.setAllowCredentials(true);
//                        config.setAllowedHeaders(Collections.singletonList("*"));
//                        config.setExposedHeaders(Arrays.asList("Authorization"));
//                        config.setMaxAge(3600L);
//                        return config;
//                    }
//                }).and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/register").permitAll()
                .requestMatchers(HttpMethod.GET,"admin/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/customers/**").hasRole("USER")
                .requestMatchers(HttpMethod.POST,"/author/**").hasRole("AUTHOR")
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(new JwtTokenGeneratorFilter(),BasicAuthenticationFilter.class)
                .addFilterBefore(new JetTokenValidatorFilter(),BasicAuthenticationFilter.class)
                .formLogin()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

}
