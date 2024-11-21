package co.edu.uniquindio.tiqueteo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {
    String name;
    String email;
    String password;
    String address;
    String phone;
    String image;


    @Id @EqualsAndHashCode.Include
    String id;

    String recoveryCode; // Clave de recuperación
    LocalDateTime recoveryCodeExpiration; // Fecha de expiración de la clave
}
