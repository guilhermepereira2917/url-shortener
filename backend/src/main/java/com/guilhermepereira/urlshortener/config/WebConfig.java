package com.guilhermepereira.urlshortener.config;

import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@Nonnull CorsRegistry registry) {
                registry.addMapping("/shorten-url")
                    .allowedOrigins("http://localhost:5173", "https://url-shortener.guilhermepereiradev.com")
                    .allowedMethods("GET", "POST");
            }
        };
    }
}
