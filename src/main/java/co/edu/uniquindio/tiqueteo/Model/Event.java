package co.edu.uniquindio.tiqueteo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    String id;

    String name;
    String address;
    String description;
    EventType type;
    String image;
    String location;
    String date;

    List<Locality> localities;

}
