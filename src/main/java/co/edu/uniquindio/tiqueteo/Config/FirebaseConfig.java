package co.edu.uniquindio.tiqueteo.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    /*@Bean
    public FirebaseApp initializeFirebase() throws IOException {
        // Obtener la ruta del archivo desde la variable de entorno "firebaseAPI"
        String firebasePath = System.getenv("firebaseAPI");

        // Validar que la variable de entorno esté definida
        if (firebasePath == null || firebasePath.isEmpty()) {
            throw new IllegalArgumentException("La variable de entorno 'firebaseAPI' no está definida.");
        }

        // Crear el FileInputStream utilizando la ruta de la variable de entorno
        FileInputStream serviceAccount = new FileInputStream(firebasePath);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        return FirebaseApp.initializeApp(options);
    }*/

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        // Obtener la ruta del archivo desde la variable de entorno "firebaseAPI"
        String firebasePath = System.getenv("firebaseAPI");

        // Validar que la variable de entorno esté definida
        if (firebasePath == null || firebasePath.isEmpty()) {
            throw new IllegalArgumentException("La variable de entorno 'firebaseAPI' no está definida.");
        }

        // Verificar si ya existe una instancia de FirebaseApp
        if (FirebaseApp.getApps().isEmpty()) {
            // Crear el FileInputStream utilizando la ruta de la variable de entorno
            FileInputStream serviceAccount = new FileInputStream(firebasePath);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            return FirebaseApp.initializeApp(options);
        } else {
            return FirebaseApp.getInstance();
        }
    }

}
