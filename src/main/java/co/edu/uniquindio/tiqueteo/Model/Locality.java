package co.edu.uniquindio.tiqueteo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Locality {
    @Id
    String id;
    String name;
    Double price;
    Integer maxCapacity;
    Integer currentCapacity;

}
