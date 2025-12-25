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
            .csrf(csrf -> csrf.disable()) // CSRFを無効化（HTMLのあの1行を消した理由です）
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // 全てのアクセスを一旦許可してループを防ぎます
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
                .logoutUrl("/logout")
                .logoutSuccessUrl("/admin/signin"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}