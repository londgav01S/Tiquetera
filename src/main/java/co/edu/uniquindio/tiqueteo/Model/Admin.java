package co.edu.uniquindio.tiqueteo.Model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")  // Guardado en la misma colección que los usuarios
public class Admin extends User{

    List<Event> eventList = new ArrayList<>();
    // Aquí puedes agregar campos específicos de Admin, si existen.
    private String role = "ADMIN";  // Por ejemplo, un campo que defina el rol del admin

}
