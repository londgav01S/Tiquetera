package co.edu.uniquindio.tiqueteo.Dto;

import co.edu.uniquindio.tiqueteo.Model.Event;
import co.edu.uniquindio.tiqueteo.Model.EventReportData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ReportDto {
    private double totalSales;
    private int totalTicketsSold;
    private List<EventReportData> events = new ArrayList<>();

    // Getters y Setters

    public void addEventData(Event event, double totalSales, int totalTicketsSold, Map<String, Double> occupancyByLocality) {
        this.events.add(new EventReportData(event, totalSales, totalTicketsSold, occupancyByLocality));
    }
}
