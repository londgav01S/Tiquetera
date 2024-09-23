package co.edu.uniquindio.tiqueteo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Purchase {

    @Id
    @EqualsAndHashCode.Include()
    String id;
    String orderCode;
    String userId;
    Double total;
    String date;
    PurchaseDetail detail;
}
