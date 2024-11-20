package co.edu.uniquindio.tiqueteo.Controller;

import co.edu.uniquindio.tiqueteo.Dto.ClientDto;
import co.edu.uniquindio.tiqueteo.Dto.LoginDto;
import co.edu.uniquindio.tiqueteo.Dto.PurchaseDto;
import co.edu.uniquindio.tiqueteo.Dto.UserDto;
import co.edu.uniquindio.tiqueteo.Model.Client;
import co.edu.uniquindio.tiqueteo.Services.iClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@CrossOrigin
public class ClientViewController {

    private final iClientService clientService;

    @Autowired
    public ClientViewController(iClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            // Llama al servicio de login y obtiene el cliente
            Client client = clientService.login(loginDto);

            // Devuelve el cliente como respuesta si el inicio de sesión es exitoso
            return ResponseEntity.ok(client);
        } catch (RuntimeException e) {
            // Devuelve un error 401 si las credenciales son incorrectas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    // Crear un nuevo admin: POST /api/admin
    @PostMapping ("/registerUser")
    public UserDto create(@RequestBody UserDto clientDto) {
        System.out.println("Entrando a create");
        return clientService.createClient(clientDto);
    }

    // Actualizar un admin: PUT /api/admin
    @PutMapping("/updateClient")
    public UserDto update(@RequestBody UserDto clientDto) {
        return clientService.updateClient(clientDto);
    }

    // Eliminar un admin por ID: DELETE /api/admin/{id}
    @DeleteMapping("/{id}/client")
    public void delete(@PathVariable String id) {
        UserDto clientDelete = clientService.getClientById(id);
        clientService.deleteClient(clientDelete);
    }

    // Obtener un admin por ID: GET /api/admin/{id}
    @GetMapping("/{id}/client")
    public UserDto getClientById(@PathVariable String id) {
        return clientService.getClientById(id);
    }
    // Obtener todos los admins: GET /api/admin/all
    @GetMapping("/allClients")
    public List<UserDto> getAllClient() {
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
