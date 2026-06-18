package com.sigat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Value("${sigat.cors.origenes-adicionales:}")
    private String origenesAdicionales;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        java.util.List<String> origenes = new java.util.ArrayList<>(java.util.List.of(
            "http://localhost:3000",
            "http://localhost:5173"
        ));

        // Origenes adicionales configurados en application.properties
        // Ejemplo: sigat.cors.origenes-adicionales=http://192.168.1.100:5173
        if (origenesAdicionales != null && !origenesAdicionales.isBlank()) {
            for (String o : origenesAdicionales.split(",")) {
                origenes.add(o.trim());
            }
        }

        config.setAllowedOrigins(origenes);

        // Metodos HTTP permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

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
