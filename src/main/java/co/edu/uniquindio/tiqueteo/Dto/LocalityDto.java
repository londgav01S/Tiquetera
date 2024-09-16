package co.edu.uniquindio.tiqueteo.Dto;

public record LocalityDto(
        String name,
        Double price,
        Integer maxCapacity,
        Integer currentCapacity
) {
}
