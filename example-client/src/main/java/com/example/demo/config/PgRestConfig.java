package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ashfaq.pgrestsdk.PgRestSdk;

@Configuration
public class PgRestConfig {
    @Bean
    public PgRestSdk pgRestSdk() {
        // Base URL can be externalized via properties
        return new PgRestSdk("http://localhost:3000");
    }
}
