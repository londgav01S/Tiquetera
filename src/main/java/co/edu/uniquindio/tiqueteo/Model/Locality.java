package co.edu.uniquindio.tiqueteo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Locality {
    @Id
    String id;
    String name;
    Double price;
    Integer maxCapacity;
    Integer currentCapacity;

}
