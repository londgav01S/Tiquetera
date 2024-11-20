package co.edu.uniquindio.tiqueteo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica CORS a todos los endpoints
                .allowedOrigins("http://localhost:3000") // Permite solicitudes desde estos orígenes
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Permite solo estos métodos HTTP
    }
}

