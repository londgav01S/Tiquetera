package co.edu.uniquindio.tiqueteo.Dto;

import co.edu.uniquindio.tiqueteo.Model.PurchaseDetail;

public record PurchaseDto(
        String id,
        String orderCode,
        String userId,
        Double total,
        String date,
        PurchaseDetail detail
) {
}
