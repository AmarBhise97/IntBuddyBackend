package com.IntBuddy.IntBuddy.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .cors(Customizer.withDefaults())

            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers(
            	        "/users/**",
            	        "/Experiance/**",
            	        "/comment/**",
            	        "/contact/**",
            	        "/anonymous/**",
            	        "/ai/**",
            	        "/resume/**"
            	    ).permitAll()
            	    .anyRequest().authenticated()
            	)
           
            .formLogin(form -> form.disable())

            .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }
}
