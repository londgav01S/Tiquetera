package co.edu.uniquindio.tiqueteo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDto {

    String id;
    String name;
    String address;
    Double ticketPrice;
    String location;
}
