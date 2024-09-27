package co.edu.uniquindio.tiqueteo.Services;

import co.edu.uniquindio.tiqueteo.Dto.ClientDto;
import co.edu.uniquindio.tiqueteo.Dto.PurchaseDto;
import co.edu.uniquindio.tiqueteo.Model.Purchase;

import java.util.List;

public interface iClientService {
     ClientDto createClient (ClientDto clientDto);
     ClientDto updateClient(ClientDto clientDto);
     void deleteClient(ClientDto clientDto);
     ClientDto getClientById(String clientId);
     List<ClientDto> getAllClient();

     PurchaseDto buyTicket(PurchaseDto purchaseDto);
     PurchaseDto cancelTicket(PurchaseDto purchaseDto);
     PurchaseDto getTicketById(String id);
     List getAllTicketsByClient(String clientId);
}
