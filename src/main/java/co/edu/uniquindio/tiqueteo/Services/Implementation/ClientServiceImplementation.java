package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Dto.ClientDto;
import co.edu.uniquindio.tiqueteo.Dto.PurchaseDto;
import co.edu.uniquindio.tiqueteo.Model.*;
import co.edu.uniquindio.tiqueteo.Repositories.EventRepository;
import co.edu.uniquindio.tiqueteo.Repositories.PurchaseRepository;
import co.edu.uniquindio.tiqueteo.Repositories.UserRepository;
import co.edu.uniquindio.tiqueteo.Services.iClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplementation implements iClientService {

    @Autowired
    private EventRepository eventRepository;  // Repositorio basado en MongoDB

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    // Convertir AdminDto a Admin (convierte de DTO a entidad)
    private Client toEntity(ClientDto clientDto) {
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setAddress(clientDto.getAddress());
        client.setPhone(clientDto.getPhone());
        return client;
    }

    // Convertir Admin a AdminDto (convierte de entidad a DTO)
    private ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), client.getName(), client.getEmail(), client.getAddress(), client.getPhone());
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client = toEntity(clientDto);  // Convierte DTO a entidad
        Client savedClient = userRepository.save(client);  // Guarda el admin en MongoDB
        return toDto(savedClient);  // Convierte entidad a DTO y devuelve
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        // Buscar el admin por su ID en la base de datos
        Optional<User> existingUser = userRepository.findById(clientDto.getId());

        // Verifica que el usuario encontrado sea un Admin
        if (existingUser.isPresent() && existingUser.get() instanceof Client) {
            Client clientToUpdate = (Client) existingUser.get();  // Hacemos casting a Admin
            // Actualizar los campos necesarios
            clientToUpdate.setName(clientDto.getName());
            clientToUpdate.setEmail(clientDto.getEmail());
            clientToUpdate.setAddress(clientDto.getAddress());
            clientToUpdate.setPhone(clientDto.getPhone());
            Client updatedClient = userRepository.save(clientToUpdate);  // Guardar cambios
            return toDto(updatedClient);  // Convertir a DTO y devolver
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + clientDto.getId());
        }
    }

    @Override
    public void deleteClient(ClientDto clientDto) {
        // Buscar el admin por su ID en la base de datos
        Optional<User> existingUser = userRepository.findById(clientDto.getId());

        // Verifica que el usuario encontrado sea un Admin
        if (existingUser.isPresent() && existingUser.get() instanceof Client) {
            userRepository.delete(existingUser.get());
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + clientDto.getId());
        }
    }

    @Override
    public ClientDto getClientById(String clientId) {
        // Buscar el admin por su ID en la base de datos
        Optional<User> user = userRepository.findById(clientId);
        if (user.isPresent() && user.get() instanceof Client) {
            return toDto((Client) user.get());  // Hacemos casting a Admin y convertimos a DTO
        } else {
            throw new RuntimeException("Client no encontrado con ID: " + clientId);
        }
    }

    @Override
    public List<ClientDto> getAllClient() {
        // Filtrar solo los usuarios que son Admin y convertir a DTO
        return userRepository.findAll().stream()
                .filter(user -> user instanceof Client)
                .map(user -> toDto((Client) user))
                .collect(Collectors.toList());
    }


    // Convertir AdminDto a Admin (convierte de DTO a entidad)
    private Purchase toEntity(PurchaseDto purchaseDto) {
        Purchase purchase = new Purchase();
        purchase.setId(purchaseDto.getId());
        purchase.setEventId(purchaseDto.getEventId());
        purchase.setClientId(purchaseDto.getClientId());
        purchase.setCant(purchaseDto.getCant());
        purchase.setTotalPrice(purchaseDto.getTotalPrice());
        purchase.setDate(purchaseDto.getDate());
        return purchase;
    }

    // Convertir Admin a AdminDto (convierte de entidad a DTO)
    private PurchaseDto toDto(Purchase purchase) {
        return new PurchaseDto(purchase.getId(), purchase.getEventId(), purchase.getClientId(), purchase.getCant(), purchase.getTotalPrice(), purchase.getDate(), purchase.isCancelled());
    }
    @Override
    public PurchaseDto buyTicket(PurchaseDto purchaseDto) {

        // Obtener el precio del boleto del evento desde el repositorio de eventos
        Event event = eventRepository.findById(purchaseDto.getEventId())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + purchaseDto.getEventId()));

        // Calcular el precio total (cantidad de tickets * precio por ticket)
        double totalPrice = purchaseDto.getCant() * event.getTicketPrice();

        // Convertir PurchaseDto a Purchase (entidad)
        Purchase purchase = new Purchase();
        purchase.setEventId(purchaseDto.getEventId());
        purchase.setClientId(purchaseDto.getClientId());
        purchase.setCant(purchaseDto.getCant());
        purchase.setTotalPrice(totalPrice);
        purchase.setDate(purchaseDto.getDate());

        // Guardar la compra en la base de datos
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // Convertir Purchase (entidad) a PurchaseDto y devolver al frontend
        return toDto(savedPurchase);
    }
    // Método para cancelar una compra de entradas
    @Override
    public PurchaseDto cancelTicket(PurchaseDto purchaseDto) {
        Optional<Purchase> existingPurchase = purchaseRepository.findById(purchaseDto.getId());

        if (existingPurchase.isPresent()) {
            Purchase purchase = existingPurchase.get();
            purchase.setCancelled(true);  // Marcamos la compra como cancelada
            Purchase updatedPurchase = purchaseRepository.save(purchase);  // Guardamos los cambios

            // Convertir a PurchaseDto y devolver
            return toDto(updatedPurchase);
        } else {
            throw new RuntimeException("Compra no encontrada con ID: " + purchaseDto.getId());
        }
    }

    // Obtener una compra por su ID
    @Override
    public PurchaseDto getTicketById(String id) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + id));
        return toDto(purchase);
    }

    // Obtener todas las compras de un cliente por su ID
    @Override
    public List<PurchaseDto> getAllTicketsByClient(String clientId) {

        List<Purchase> purchases = purchaseRepository.findByClientId(clientId);

        // Convertir la lista de Purchase (entidades) a PurchaseDto
        return purchases.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    }

