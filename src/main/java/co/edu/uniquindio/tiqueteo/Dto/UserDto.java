package co.edu.uniquindio.tiqueteo.Dto;

import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

public record UserDto(
        String name,
        String email,
        String password,
        String address,
        String phone,
        String idUser,
        String id
) {
}
