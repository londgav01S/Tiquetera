package co.edu.uniquindio.tiqueteo.Dto;

import co.edu.uniquindio.tiqueteo.Model.Enums.PurchaseState;

public record PurchaseDetailDto(
        String id,
        String payMethod,
        PurchaseState state,
        String reference
) {

}
