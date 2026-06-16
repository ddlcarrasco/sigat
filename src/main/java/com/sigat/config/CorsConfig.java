package com.sigat.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Origenes permitidos — agrega aqui la URL del front cuando la tengas
        config.setAllowedOrigins(List.of(
            "http://localhost:3000",
            "http://localhost:5173"
        ));

        // Metodos HTTP permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Headers que puede enviar el front (incluye Authorization para el JWT)
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        // Permite que el front lea el header Authorization en las respuestas
        config.setExposedHeaders(List.of("Authorization"));

        // Permite enviar cookies o credenciales si se necesitan en el futuro
        config.setAllowCredentials(true);

        // Cuanto tiempo el navegador cachea la respuesta del preflight (en segundos)
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
