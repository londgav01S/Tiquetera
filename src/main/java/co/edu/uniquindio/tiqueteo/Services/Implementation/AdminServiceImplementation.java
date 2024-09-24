package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Dto.AdminDto;
import co.edu.uniquindio.tiqueteo.Dto.EventDto;
import co.edu.uniquindio.tiqueteo.Model.Admin;
import co.edu.uniquindio.tiqueteo.Model.Event;
import co.edu.uniquindio.tiqueteo.Model.User;
import co.edu.uniquindio.tiqueteo.Repositories.EventRepository;
import co.edu.uniquindio.tiqueteo.Repositories.UserRepository;
import co.edu.uniquindio.tiqueteo.Services.iAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImplementation implements iAdminService {

    @Autowired
    private EventRepository eventRepository;  // Repositorio basado en MongoDB

    @Autowired
    private UserRepository userRepository;

    // Convertir AdminDto a Admin (convierte de DTO a entidad)
    private Admin toEntity(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setId(adminDto.getId());
        admin.setName(adminDto.getName());
        admin.setEmail(adminDto.getEmail());
        admin.setAddress(adminDto.getAddress());
        admin.setPhone(adminDto.getPhone());
        admin.setRole(adminDto.getRole() != null ? adminDto.getRole() : "ADMIN");  // Asigna rol predeterminado si no se pasa
        return admin;
    }

    // Convertir Admin a AdminDto (convierte de entidad a DTO)
    private AdminDto toDto(Admin admin) {
        return new AdminDto(admin.getId(), admin.getName(), admin.getEmail(), admin.getAddress(), admin.getPhone(), admin.getRole());
    }

    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        Admin admin = toEntity(adminDto);  // Convierte DTO a entidad
        Admin savedAdmin = userRepository.save(admin);  // Guarda el admin en MongoDB
        return toDto(savedAdmin);  // Convierte entidad a DTO y devuelve
    }

    @Override
    public AdminDto updateAdmin(AdminDto adminDto) {
        // Buscar el admin por su ID en la base de datos
        Optional<User> existingUser = userRepository.findById(adminDto.getId());

        // Verifica que el usuario encontrado sea un Admin
        if (existingUser.isPresent() && existingUser.get() instanceof Admin) {
            Admin adminToUpdate = (Admin) existingUser.get();  // Hacemos casting a Admin
            // Actualizar los campos necesarios
            adminToUpdate.setName(adminDto.getName());
            adminToUpdate.setEmail(adminDto.getEmail());
            adminToUpdate.setAddress(adminDto.getAddress());
            adminToUpdate.setPhone(adminDto.getPhone());
            Admin updatedAdmin = userRepository.save(adminToUpdate);  // Guardar cambios
            return toDto(updatedAdmin);  // Convertir a DTO y devolver
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + adminDto.getId());
        }
    }

    @Override
    public void deleteAdmin(AdminDto adminDto) {
        // Buscar el admin por su ID en la base de datos
        Optional<User> existingUser = userRepository.findById(adminDto.getId());

        // Verifica que el usuario encontrado sea un Admin
        if (existingUser.isPresent() && existingUser.get() instanceof Admin) {
            userRepository.delete(existingUser.get());
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + adminDto.getId());
        }
    }

    @Override
    public AdminDto getAdminById(String adminId) {
        // Buscar el admin por su ID en la base de datos
        Optional<User> user = userRepository.findById(adminId);
        if (user.isPresent() && user.get() instanceof Admin) {
            return toDto((Admin) user.get());  // Hacemos casting a Admin y convertimos a DTO
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + adminId);
        }
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        // Filtrar solo los usuarios que son Admin y convertir a DTO
        return userRepository.findAll().stream()
                .filter(user -> user instanceof Admin)
                .map(user -> toDto((Admin) user))
                .collect(Collectors.toList());
    }

    // Crear un nuevo evento
    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(String eventId, Event eventDetails) {
        Optional<Event> existingEvent = eventRepository.findById(eventId);

        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();
            event.setName(eventDetails.getName());
            event.setDescription(eventDetails.getDescription());
            event.setDate(eventDetails.getDate());
            event.setLocation(eventDetails.getLocation());
            return eventRepository.save(event);
        } else {
            throw new RuntimeException("Evento no encontrado con ID: " + eventId);
        }
    }

    @Override
    public void deleteEvent(String eventId) {
        Optional<Event> existingEvent = eventRepository.findById(eventId);

        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();
            //event.setActive(false);  // Marcar como inactivo en lugar de eliminar
            eventRepository.save(event);
        } else {
            throw new RuntimeException("Evento no encontrado con ID: " + eventId);
        }
    }



    // Obtener todos los eventos
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(String eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + eventId));
    }

}


