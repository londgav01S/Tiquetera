package co.edu.uniquindio.tiqueteo.Model;

import co.edu.uniquindio.tiqueteo.Model.Enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document("events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id @EqualsAndHashCode.Include
    String id;

    String name;
    String address;
    String description;
    EventType type;
    String image;
    String location;
    LocalDate date;

    List<Locality> localities;

}
