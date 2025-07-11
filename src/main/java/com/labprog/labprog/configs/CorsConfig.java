package com.labprog.labprog.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173",
                                "https://system-lab-prog-front-q1oo.vercel.app",
                                "https://classy-bublanina-a5784e.netlify.app/",
                                "https://system-lab-prog-front-production.up.railway.app")
                        .allowedMethods("GET", "PUT", "DELETE", "POST")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
