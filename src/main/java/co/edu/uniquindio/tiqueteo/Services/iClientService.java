package co.edu.uniquindio.tiqueteo.Services;

import co.edu.uniquindio.tiqueteo.Dto.PurchaseDto;
import co.edu.uniquindio.tiqueteo.Model.Admin;
import co.edu.uniquindio.tiqueteo.Model.Client;
import co.edu.uniquindio.tiqueteo.Model.Purchase;

import java.util.List;

public interface iClientService {
     Client createClient (Client client);
     Client updateClient(Client client);
     void deleteClient(Client client);
     Client getClientById(String clientId);
     List<Client> getAllClient();

     Purchase buyTicket(PurchaseDto purchase);
     Purchase cancelTicket(PurchaseDto purchase);
     Purchase getTicketById(int id);
     Purchase getAllTicketsByClient(int clientId);
}
