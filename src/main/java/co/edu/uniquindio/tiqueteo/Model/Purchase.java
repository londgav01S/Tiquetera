package co.edu.uniquindio.tiqueteo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document ("purchases")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Purchase {

    @Id
    @EqualsAndHashCode.Include()
    String id;

    private String eventId;
    private String clientId;
    private Integer cant;
    private Double totalPrice;
    private LocalDateTime date;
    private String localityId;
    private boolean cancelled = false;  // Indica si la compra ha sido cancelada (por defecto false)
    PurchaseDetail detail;
}
