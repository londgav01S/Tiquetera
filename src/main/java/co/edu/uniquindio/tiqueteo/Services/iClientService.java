package co.edu.uniquindio.tiqueteo.Services;

import co.edu.uniquindio.tiqueteo.Dto.ClientDto;
import co.edu.uniquindio.tiqueteo.Dto.LoginDto;
import co.edu.uniquindio.tiqueteo.Dto.PurchaseDto;
import co.edu.uniquindio.tiqueteo.Dto.UserDto;
import co.edu.uniquindio.tiqueteo.Model.Purchase;

import java.util.List;

public interface iClientService {
     boolean login (LoginDto loginDto);
     UserDto createClient (UserDto clientDto);
     UserDto updateClient(UserDto clientDto);
     void deleteClient(UserDto clientDto);
     UserDto getClientById(String clientId);
     List<UserDto> getAllClient();

     PurchaseDto buyTicket(PurchaseDto purchaseDto);
     PurchaseDto cancelTicket(PurchaseDto purchaseDto);
     PurchaseDto getTicketById(String id);
     List getAllTicketsByClient(String clientId);

}
