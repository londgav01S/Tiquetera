package co.edu.uniquindio.tiqueteo.Services;

import co.edu.uniquindio.tiqueteo.Dto.PurchaseDto;
import co.edu.uniquindio.tiqueteo.Model.Purchase;

public interface iClientService {
     Purchase buyTicket(PurchaseDto purchase);
     Purchase cancelTicket(PurchaseDto purchase);
     Purchase getTicketById(int id);
     Purchase getAllTicketsByClient(int clientId);
}
