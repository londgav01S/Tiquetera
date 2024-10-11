package co.edu.uniquindio.tiqueteo.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RangeDateDto {
    private LocalDate startDate ;
    private LocalDate endDate;
}