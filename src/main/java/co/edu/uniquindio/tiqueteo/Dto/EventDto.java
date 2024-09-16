package co.edu.uniquindio.tiqueteo.Dto;

import co.edu.uniquindio.tiqueteo.Model.Enums.EventType;
import co.edu.uniquindio.tiqueteo.Model.Locality;

import java.time.LocalDate;
import java.util.List;

public record EventDto(
        String id,

        String name,
        String address,
        String description,
        EventType type,
        String image,
        LocalDate location,
        String date,

        List<Locality>localities
) {
}
