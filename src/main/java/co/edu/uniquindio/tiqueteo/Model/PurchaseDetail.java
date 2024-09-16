package co.edu.uniquindio.tiqueteo.Model;


import co.edu.uniquindio.tiqueteo.Model.Enums.PurchaseState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetail {

    @Id
    String id;
    String payMethod;
    PurchaseState state;
    String reference;

}
