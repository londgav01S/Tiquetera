package co.edu.uniquindio.tiqueteo.Dto;

import java.io.Serializable;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminDto {
    private String id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String role;  // Podría ser ADMIN por defecto

}
