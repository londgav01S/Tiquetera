package co.edu.uniquindio.tiqueteo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Id @EqualsAndHashCode.Include
    String id;
    String code;
    String name;
    Double percentage;
    String date;

}
