package co.edu.uniquindio.tiqueteo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class EventReportData {
    private String eventName;
    private double totalSales;
    private int totalTicketsSold;
    private Map<String, Double> occupancyByLocality;

    public EventReportData(Event event, double totalSales, int totalTicketsSold, Map<String, Double> occupancyByLocality) {
        this.eventName = event.getName();
        this.totalSales = totalSales;
        this.totalTicketsSold = totalTicketsSold;
        this.occupancyByLocality = occupancyByLocality;
    }
}
