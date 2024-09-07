package co.edu.uniquindio.tiqueteo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    @EqualsAndHashCode.Include()
    String id;
    String orderCode;
    String userId;
    Double total;
    String date;
    PurcheseDetail detail;
}
