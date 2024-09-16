package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Dto.PurchaseDto;
import co.edu.uniquindio.tiqueteo.Model.Purchase;
import co.edu.uniquindio.tiqueteo.Services.iClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImplementation implements iClientService {
    @Override
    public Purchase buyTicket(PurchaseDto purchase) {
        return null;
    }

    @Override
    public Purchase cancelTicket(PurchaseDto purchase) {
        return null;
    }

    @Override
    public Purchase getTicketById(int id) {
        return null;
    }

    @Override
    public Purchase getAllTicketsByClient(int clientId) {
        return null;
    }
}
