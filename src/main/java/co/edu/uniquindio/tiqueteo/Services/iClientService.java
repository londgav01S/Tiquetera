package co.edu.uniquindio.tiqueteo.Services;

import co.edu.uniquindio.tiqueteo.Dto.PurchaseDto;
import co.edu.uniquindio.tiqueteo.Model.Purchase;

public interface iClientService {
    public Purchase buyTicket(PurchaseDto purchase);
    public Purchase cancelTicket(PurchaseDto purchase);
    public Purchase getTicketById(int id);
    public Purchase getAllTicketsByClient(int clientId);
}
