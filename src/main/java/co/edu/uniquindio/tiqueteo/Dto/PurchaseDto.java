package co.edu.uniquindio.tiqueteo.Dto;

import co.edu.uniquindio.tiqueteo.Model.Enums.EventType;
import co.edu.uniquindio.tiqueteo.Model.PurchaseDetail;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseDto {
    private String id;
    private String eventId;
    private String clientId;
    private Integer cant;
    private Double totalPrice;
    private Date date;
    private boolean cancelled = false;  // Indica si la compra ha sido cancelada (por defecto false)
}
