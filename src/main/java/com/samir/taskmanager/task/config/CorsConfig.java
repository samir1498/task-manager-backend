package com.samir.taskmanager.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Specify the URL path you want to enable CORS for
                .allowedOrigins("*") // Add the origin of your React frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}

