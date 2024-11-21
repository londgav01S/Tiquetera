package co.edu.uniquindio.tiqueteo.Dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CouponDto {
    private String id;
    private String code;
    private String name;
    private Double percentage;
    private String date;
}
