package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Dto.ClientDto;
import co.edu.uniquindio.tiqueteo.Dto.LoginDto;
import co.edu.uniquindio.tiqueteo.Dto.PurchaseDto;
import co.edu.uniquindio.tiqueteo.Dto.UserDto;
import co.edu.uniquindio.tiqueteo.Model.*;
import co.edu.uniquindio.tiqueteo.Repositories.EventRepository;
import co.edu.uniquindio.tiqueteo.Repositories.PurchaseRepository;
import co.edu.uniquindio.tiqueteo.Repositories.UserRepository;
import co.edu.uniquindio.tiqueteo.Services.iClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Autowired
    private EmailServiceImplementation emailService; ;


    @Override
    public Client login(LoginDto loginDto) {

        String email = loginDto.getEmail().toLowerCase();
        System.out.println(email);
        // Buscar el cliente por email
        User user = userRepository.findByEmail(email);

        // Verificar si el usuario existe y es de tipo Client
        if (user != null && user instanceof Client) {
            Client client = (Client) user;

            // Verificar la contraseña directamente (sin BCrypt)
            if (loginDto.getPassword().equals(client.getPassword())) {
                return client;  // Retornar el cliente si el inicio de sesión es exitoso
            }
        }

        throw new RuntimeException("Credenciales incorrectas");  // Lanza una excepción si las credenciales son incorrectas
}
    // Convertir AdminDto a Admin (convierte de DTO a entidad)
    private Client toEntity(UserDto clientDto) {
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setAddress(clientDto.getAddress());
        client.setPhone(clientDto.getPhone());
        client.setPassword(clientDto.getPassword());
        return client;
    }

    // Convertir Admin a AdminDto (convierte de entidad a DTO)
    private UserDto toDto(Client client) {
        return new UserDto(client.getId(), client.getName(), client.getEmail(), client.getAddress(), client.getPhone(), client.getPassword(), "CLIENT");
    }

    @Override
    public UserDto createClient(UserDto clientDto) {
        Client client = toEntity(clientDto);  // Convierte DTO a entidad
        Client savedClient = userRepository.save(client);  // Guarda el admin en MongoDB
        emailService.sendEmail(client.getEmail());  // Enviar correo de confirmación
        return toDto(savedClient);  // Convierte entidad a DTO y devuelve

    }

/*    @Override
    public UserDto updateClient(UserDto clientDto) {
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
    }*/

    @Override
    public UserDto updateClient(UserDto clientDto) {
    // Buscar el cliente por su ID en la base de datos
        Optional<User> existingUser = userRepository.findById(clientDto.getId());

        // Verifica que el usuario encontrado sea un Client
        if (existingUser.isPresent() && existingUser.get() instanceof Client) {
            Client clientToUpdate = (Client) existingUser.get();  // Hacemos casting a Client
            // Actualizar los campos necesarios
            clientToUpdate.setName(clientDto.getName());
            clientToUpdate.setEmail(clientDto.getEmail());
            clientToUpdate.setAddress(clientDto.getAddress());
            clientToUpdate.setPhone(clientDto.getPhone());
            clientToUpdate.setPassword(clientDto.getPassword()); // Asegúrate de actualizar la contraseña
            Client updatedClient = userRepository.save(clientToUpdate);  // Guardar cambios
            return toDto(updatedClient);  // Convertir a DTO y devolver
        } else {
            throw new RuntimeException("Client no encontrado con ID: " + clientDto.getId());
        }
    }

    @Override
    public void deleteClient(UserDto clientDto) {
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
    public UserDto getClientById(String clientId) {
        // Buscar el admin por su ID en la base de datos
        Optional<User> user = userRepository.findById(clientId);
        if (user.isPresent() && user.get() instanceof Client) {
            return toDto((Client) user.get());  // Hacemos casting a Admin y convertimos a DTO
        } else {
            throw new RuntimeException("Client no encontrado con ID: " + clientId);
        }
    }

    @Override
    public List<UserDto> getAllClient() {
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
        return new PurchaseDto(purchase.getId(), purchase.getEventId(), purchase.getClientId(), purchase.getCant(), purchase.getTotalPrice(), purchase.getDate(), purchase.getLocalityId(), purchase.isCancelled());
    }
    @Override
    public PurchaseDto buyTicket(PurchaseDto purchaseDto) {

        // Obtener el precio del boleto del evento desde el repositorio de eventos
        Event event = eventRepository.findById(purchaseDto.getEventId())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + purchaseDto.getEventId()));

        // Buscar la localidad seleccionada dentro del evento
        Locality selectedLocality = event.getLocalities().stream()
                .filter(locality -> locality.getId().equals(purchaseDto.getLocalityId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Localidad no encontrada con ID: " + purchaseDto.getLocalityId()));

        // Validar si hay suficientes entradas disponibles en la localidad seleccionada
        if (selectedLocality.getCurrentCapacity() + purchaseDto.getCant() > selectedLocality.getMaxCapacity()) {
            throw new RuntimeException("No hay suficientes entradas disponibles en la localidad seleccionada.");
        }

        // Calcular el precio total (cantidad de tickets * precio de la localidad)
        double totalPrice = purchaseDto.getCant() * selectedLocality.getPrice();

        // Actualizar la capacidad actual de la localidad
        selectedLocality.setCurrentCapacity(selectedLocality.getCurrentCapacity() + purchaseDto.getCant());

        // Convertir PurchaseDto a Purchase (entidad)
        Purchase purchase = new Purchase();
        purchase.setEventId(purchaseDto.getEventId());
        purchase.setClientId(purchaseDto.getClientId());
        purchase.setCant(purchaseDto.getCant());
        purchase.setTotalPrice(totalPrice);
        purchase.setDate(LocalDateTime.now());
        purchase.setLocalityId(selectedLocality.getName());  // Guardar el nombre de la localidad en la compra

        // Guardar la compra en la base de datos
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // Actualizar la lista de localidades del evento y guardar el evento
        eventRepository.save(event);
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

