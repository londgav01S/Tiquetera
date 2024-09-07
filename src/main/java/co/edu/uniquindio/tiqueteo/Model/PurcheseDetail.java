package co.edu.uniquindio.tiqueteo.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurcheseDetail {

    @Id
    String id;
    String payMethod;
    PurcheseState state;
    String reference;

}
