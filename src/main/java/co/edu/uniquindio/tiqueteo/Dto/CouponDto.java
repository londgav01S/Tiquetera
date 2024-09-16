package co.edu.uniquindio.tiqueteo.Dto;

public record CouponDto(
        String id,
        String code,
        String name,
        Double percentage,
        String date
) {
}
