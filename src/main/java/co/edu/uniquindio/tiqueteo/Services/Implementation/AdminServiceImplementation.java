package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Dto.EventDto;
import co.edu.uniquindio.tiqueteo.Dto.UserDto;
import co.edu.uniquindio.tiqueteo.Model.Admin;
import co.edu.uniquindio.tiqueteo.Model.Client;
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
    private Admin toEntity(UserDto userDto) {
        Admin admin = new Admin();
        admin.setId(userDto.getId());
        admin.setName(userDto.getName());
        admin.setEmail(userDto.getEmail());
        admin.setAddress(userDto.getAddress());
        admin.setPhone(userDto.getPhone());
        admin.setRole(userDto.getRole() != null ? userDto.getRole() : "ADMIN");  // Asigna rol predeterminado si no se pasa
        return admin;
    }

    // Convertir Admin a AdminDto (convierte de entidad a DTO)
    private UserDto toDto(Admin admin) {
        return new UserDto(admin.getId(), admin.getName(), admin.getEmail(), admin.getAddress(), admin.getPhone(), admin.getRole());
    }

    @Override
    public UserDto createAdmin(UserDto userDto) {
        Admin admin = toEntity(userDto);  // Convierte DTO a entidad
        Admin savedAdmin = userRepository.save(admin);  // Guarda el admin en MongoDB
        return toDto(savedAdmin);  // Convierte entidad a DTO y devuelve
    }

    @Override
    public UserDto updateAdmin(UserDto userDto) {
        // Buscar el admin por su ID en la base de datos
        Optional<User> existingUser = userRepository.findById(userDto.getId());

        // Verifica que el usuario encontrado sea un Admin
        if (existingUser.isPresent() && existingUser.get() instanceof Admin) {
            Admin adminToUpdate = (Admin) existingUser.get();  // Hacemos casting a Admin
            // Actualizar los campos necesarios
            adminToUpdate.setName(userDto.getName());
            adminToUpdate.setEmail(userDto.getEmail());
            adminToUpdate.setAddress(userDto.getAddress());
            adminToUpdate.setPhone(userDto.getPhone());
            Admin updatedAdmin = userRepository.save(adminToUpdate);  // Guardar cambios
            return toDto(updatedAdmin);  // Convertir a DTO y devolver
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + userDto.getId());
        }
    }

    @Override
    public void deleteAdmin(UserDto userDto) {
        // Buscar el admin por su ID en la base de datos
        Optional<User> existingUser = userRepository.findById(userDto.getId());

        // Verifica que el usuario encontrado sea un Admin
        if (existingUser.isPresent() && existingUser.get() instanceof Admin) {
            userRepository.delete(existingUser.get());
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + userDto.getId());
        }
    }

    @Override
    public UserDto getAdminById(String adminId) {
        // Buscar el admin por su ID en la base de datos
        Optional<User> user = userRepository.findById(adminId);
        if (user.isPresent() && user.get() instanceof Admin) {
            return toDto((Admin) user.get());  // Hacemos casting a Admin y convertimos a DTO
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + adminId);
        }
    }

    @Override
    public List<UserDto> getAllAdmins() {
        // Filtrar solo los usuarios que son Admin y convertir a DTO
        return userRepository.findAll().stream()
                .filter(user -> user instanceof Admin)
                .map(user -> toDto((Admin) user))
                .collect(Collectors.toList());
    }

    // Convertir AdminDto a Admin (convierte de DTO a entidad)
    private Event toEntity(EventDto eventDto) {
        Event event = new Event();
        event.setId(eventDto.getId());
        event.setName(eventDto.getName());
        event.setAddress(eventDto.getAddress());
        event.setTicketPrice(eventDto.getTicketPrice());
        event.setLocation(eventDto.getLocation());
        return event;
    }

    // Convertir Admin a AdminDto (convierte de entidad a DTO)
    private EventDto toDto(Event event) {
        return new EventDto(event.getId(), event.getName(), event.getAddress(), event.getTicketPrice(), event.getLocation());
    }

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = toEntity(eventDto);  // Convierte DTO a entidad
        Event savedEvent = eventRepository.save(event);  // Guarda el admin en MongoDB
        return toDto(savedEvent);  // Convierte entidad a DTO y devuelve

    }

    @Override
    public EventDto updateEvent(EventDto eventDto) {
        // Buscar el admin por su ID en la base de datos
        Optional<Event> existingEvent = eventRepository.findById(eventDto.getId());

        // Verifica que el usuario encontrado sea un Admin
        if (existingEvent.isPresent() ) {
            Event eventToUpdate = existingEvent.get();  // Hacemos casting a Admin
            // Actualizar los campos necesarios
            eventToUpdate.setName(eventDto.getName());
            eventToUpdate.setAddress(eventDto.getAddress());
            eventToUpdate.setTicketPrice(eventDto.getTicketPrice());
            Event updatedEvent = eventRepository.save(eventToUpdate);  // Guardar cambios
            return toDto(updatedEvent);  // Convertir a DTO y devolver
        } else {
            throw new RuntimeException("Event not found con ID: " + eventDto.getId());
        }
    }

    @Override
    public void deleteEvent(EventDto eventdto) {// Buscar el admin por su ID en la base de datos
        Optional<Event> existingEvent = eventRepository.findById(eventdto.getId());

        // Verifica que el usuario encontrado sea un Admin
        if (existingEvent.isPresent() ) {
            eventRepository.delete(existingEvent.get());
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + eventdto.getId());
        }

    }

    @Override
    public EventDto getEventById(String id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if(eventOptional.isPresent()){
            return toDto((Event) eventOptional.get());
        }else{
            throw new RuntimeException("event not found with ID: " + id);
        }

    }

    @Override
    public List<EventDto> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}


