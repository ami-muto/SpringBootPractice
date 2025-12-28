package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
             // ログイン不要
                .requestMatchers("/admin/signup", "/admin/signin", "/contacts/**").permitAll()
                // それ以外はすべてログインが必要
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/admin/signin")
                .loginProcessingUrl("/admin/signin")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin/contacts", true)
                .failureUrl("/admin/signin?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/signin"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}