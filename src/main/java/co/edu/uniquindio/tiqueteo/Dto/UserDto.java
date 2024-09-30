package co.edu.uniquindio.tiqueteo.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String password;
    private String role;  // Podría ser ADMIN por defecto

}
