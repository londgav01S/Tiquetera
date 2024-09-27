package co.edu.uniquindio.tiqueteo.Model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Getter
@Setter
public class Client extends User{
}
