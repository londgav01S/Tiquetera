package co.edu.uniquindio.tiqueteo.Controller;

import co.edu.uniquindio.tiqueteo.Dto.ClientDto;
import co.edu.uniquindio.tiqueteo.Dto.PurchaseDto;
import co.edu.uniquindio.tiqueteo.Dto.UserDto;
import co.edu.uniquindio.tiqueteo.Model.Admin;
import co.edu.uniquindio.tiqueteo.Model.Client;
import co.edu.uniquindio.tiqueteo.Model.Purchase;
import co.edu.uniquindio.tiqueteo.Services.iAdminService;
import co.edu.uniquindio.tiqueteo.Services.iClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientViewController {
    @Autowired
    private iClientService clientService;

    // Crear un nuevo admin: POST /api/admin
    @PostMapping ("/createClient")
    public ClientDto create(@RequestBody ClientDto clientDto) {
        return clientService.createClient(clientDto);
    }

    // Actualizar un admin: PUT /api/admin
    @PutMapping("/updateClient")
    public ClientDto update(@RequestBody ClientDto clientDto) {
        return clientService.updateClient(clientDto);
    }

    // Eliminar un admin por ID: DELETE /api/admin/{id}
    @DeleteMapping("/{id}/client")
    public void delete(@PathVariable String id) {
        ClientDto clientDelete = clientService.getClientById(id);
        clientService.deleteClient(clientDelete);
    }

    // Obtener un admin por ID: GET /api/admin/{id}
    @GetMapping("/{id}/client")
    public ClientDto getClientById(@PathVariable String id) {
        return clientService.getClientById(id);
    }
    // Obtener todos los admins: GET /api/admin/all
    @GetMapping("/allClients")
    public List<ClientDto> getAllClient() {
        return clientService.getAllClient();
    }

    // Crear una nueva compra
    @PostMapping("/buyTicket")
    public PurchaseDto buyTicket(@RequestBody PurchaseDto purchaseDto) {
        return clientService.buyTicket(purchaseDto);
    }

    // Actualizar estado de compra
    @PutMapping("/updatePurchase")
    public PurchaseDto updatePurchaseState(@RequestBody PurchaseDto purchaseDto) {
        return clientService.cancelTicket(purchaseDto);
    }


    // Obtener una compra por su id
    @GetMapping("/{id}/purchase")
    public PurchaseDto getPurchaseById(@PathVariable String id) {
        return clientService.getTicketById(id);
    }

    // Obtener todas las compras de un cliente con su id
    @GetMapping("/{id}/allPurchases")
    public List<PurchaseDto> getAllPurchases(@PathVariable String id) {
        return clientService.getAllTicketsByClient(id);
    }

}
