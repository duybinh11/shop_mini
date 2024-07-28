package com.example.spring_mini.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {
    
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    // @Bean
    // public AuthenticationManager authManager(HttpSecurity http, AuthenticationProvider provider) throws Exception {
    //     AuthenticationManagerBuilder authenticationManagerBuilder = new AuthenticationManagerBuilder(http.getSharedObject(ObjectPostProcessor.class));
    //     authenticationManagerBuilder.authenticationProvider(provider);
    //     return authenticationManagerBuilder.build();
    // }
}