package co.edu.uniquindio.tiqueteo.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDto {
    private String id;
    private String name;
    private String email;
    private String address;
    private String phone;
}
