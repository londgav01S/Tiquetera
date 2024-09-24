package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Dto.PurchaseDto;
import co.edu.uniquindio.tiqueteo.Model.Admin;
import co.edu.uniquindio.tiqueteo.Model.Client;
import co.edu.uniquindio.tiqueteo.Model.Purchase;
import co.edu.uniquindio.tiqueteo.Model.User;
import co.edu.uniquindio.tiqueteo.Repositories.UserRepository;
import co.edu.uniquindio.tiqueteo.Services.iClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImplementation implements iClientService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Client createClient(Client client) {
        return userRepository.save(client);  // Guardar el administrador en MongoDB
    }

    @Override
    public Client updateClient(Client client) {
        // Buscar el admin por su ID en la base de datos
        Optional<User> existingUser = userRepository.findById(client.getId());

        if (existingUser.isPresent() && existingUser.get() instanceof Client) {
            // Actualizar los campos necesarios
            Client updatedClient = (Client) existingUser.get();
            updatedClient.setName(client.getName());
            updatedClient.setEmail(client.getEmail());
            updatedClient.setAddress(client.getAddress());
            updatedClient.setPhone(client.getPhone());
            updatedClient.setPassword(client.getPassword());  // Considera si quieres o no cambiar la contraseña

            return userRepository.save(updatedClient);  // Guardar los cambios en la base de datos
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + client.getId());
        }
    }

    @Override
    public void deleteClient(Client client) {
        Optional<User> existingUser = userRepository.findById(client.getId());

        if (existingUser.isPresent() && existingUser.get() instanceof Admin) {
            userRepository.delete(existingUser.get());  // Elimina el administrador de la base de datos
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + client.getId());
        }
    }

    @Override
    public Client getClientById(String clientId) {
        Optional<User> user = userRepository.findById(clientId);
        if (user.isPresent() && user.get() instanceof Client) {
            return (Client) user.get();  // Hacer casting de User a Admin
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + clientId);
        }
    }

    @Override
    public List<Client> getAllClient() {
        return List.of();
    }

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
