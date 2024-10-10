package co.edu.uniquindio.tiqueteo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/log", "/login").permitAll() // Permitir acceso a login y la raíz
                        .anyRequest().authenticated() // Otras rutas requieren autenticación
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login") // Página de inicio de sesión personalizada
                        .defaultSuccessUrl("/dashboard", true) // Redirige a /dashboard después de iniciar sesión exitosamente
                        .userInfoEndpoint(userInfoEndpoint ->
                                userInfoEndpoint.oidcUserService(oidcUserService()) // Configuración del servicio OIDC
                        )
                );

        // Configuración de CORS
        http.cors(cors -> cors
                .configurationSource(corsConfigurationSource())
        );

        // Deshabilitar CSRF si es necesario para las solicitudes REST
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
    @Bean
    public OidcUserService oidcUserService() {
        return new OidcUserService();
    }

    // Configuración de CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Configura los orígenes permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Métodos permitidos
        configuration.setAllowedHeaders(List.of("*")); // Permitir todos los encabezados
        configuration.setAllowCredentials(true); // Permitir credenciales
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplicar configuración a todas las rutas
        return source;
    }


}
