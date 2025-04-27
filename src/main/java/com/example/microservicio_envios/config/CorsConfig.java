package com.example.microservicio_envios.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration 
public class CorsConfig {

    @Bean // 🔁 Registra este configurador como un Bean que Spring puede utilizar
    public WebMvcConfigurer corsConfigurer() {
        // Devuelve una implementación de WebMvcConfigurer
        // que va a personalizar las reglas de CORS

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Aquí definimos las reglas de CORS

                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
                
            }
        };
    }
}