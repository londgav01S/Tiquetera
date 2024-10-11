package co.edu.uniquindio.tiqueteo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*") // Permitir todas las cabeceras
                .exposedHeaders("Set-Cookie") // Si necesitas exponer headers personalizados
                .allowCredentials(true)
                .maxAge(3600); // Duración de la caché para las solicitudes preflight
    }
}
