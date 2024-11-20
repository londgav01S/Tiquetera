package co.edu.uniquindio.tiqueteo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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


    @Id @EqualsAndHashCode.Include
    String id;

}
