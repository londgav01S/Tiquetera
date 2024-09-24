package co.edu.uniquindio.tiqueteo.Controller;

import co.edu.uniquindio.tiqueteo.Model.Admin;
import co.edu.uniquindio.tiqueteo.Model.Client;
import co.edu.uniquindio.tiqueteo.Services.iAdminService;
import co.edu.uniquindio.tiqueteo.Services.iClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientViewController {
    @Autowired
    private iClientService clientService;

    // Crear un nuevo admin: POST /api/admin
    @PostMapping
    public Client create(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    // Actualizar un admin: PUT /api/admin
    @PutMapping
    public Client update(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    // Eliminar un admin por ID: DELETE /api/admin/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        Client clientDelete = clientService.getClientById(id);
        clientService.deleteClient(clientDelete);
    }

    // Obtener un admin por ID: GET /api/admin/{id}
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable String id) {
        return clientService.getClientById(id);
    }
}
