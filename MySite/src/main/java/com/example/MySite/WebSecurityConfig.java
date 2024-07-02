package com.example.MySite;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig {
	
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  InMemoryUserDetailsManager userDetailsService() {
    UserDetails admin = User
        .withUsername("admin")
        .password(passwordEncoder().encode("admin1234"))
        .roles("ADMIN")
        .build();
    UserDetails student = User
        .withUsername("student")
        .password(passwordEncoder().encode("student5678"))
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(admin, student);
  }
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login //  フォーム認証を使う
        .permitAll()) //  フォーム認証画面は認証不要
        .authorizeHttpRequests(authz -> authz
        	.requestMatchers("/**").permitAll()
            .requestMatchers("/css/**").permitAll()
            .requestMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated()
        );
    http.csrf().disable();
    http.headers().frameOptions().disable();
    
    return http.build();
  }
  
}