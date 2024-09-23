package co.edu.uniquindio.tiqueteo.Services.Implementation;

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

@Service
public class AdminServiceImplementation implements iAdminService {

    @Autowired
    private EventRepository eventRepository;  // Repositorio basado en MongoDB

    @Autowired
    private UserRepository userRepository;

    // Crear un nuevo administrador
    @Override
    public Admin createAdmin(Admin admin) {
        return userRepository.save(admin);  // Guardar el administrador en MongoDB
    }


    // Actualizar un administrador existente
    @Override
    public Admin updateAdmin(Admin admin) {
        // Buscar el admin por su ID en la base de datos
        Optional<User> existingUser = userRepository.findById(admin.getId());

        if (existingUser.isPresent() && existingUser.get() instanceof Admin) {
            // Actualizar los campos necesarios
            Admin updatedAdmin = (Admin) existingUser.get();
            updatedAdmin.setName(admin.getName());
            updatedAdmin.setEmail(admin.getEmail());
            updatedAdmin.setAddress(admin.getAddress());
            updatedAdmin.setPhone(admin.getPhone());
            updatedAdmin.setPassword(admin.getPassword());  // Considera si quieres o no cambiar la contraseña

            return userRepository.save(updatedAdmin);  // Guardar los cambios en la base de datos
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + admin.getId());
        }
    }

    // Eliminar un administrador (de manera lógica o física, según prefieras)
    @Override
    public void deleteAdmin(Admin admin) {
        Optional<User> existingUser = userRepository.findById(admin.getId());

        if (existingUser.isPresent() && existingUser.get() instanceof Admin) {
            userRepository.delete(existingUser.get());  // Elimina el administrador de la base de datos
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + admin.getId());
        }
    }

    // Obtener un administrador por su ID
    @Override
    public Admin getAdminById(String adminId) {
        Optional<User> user = userRepository.findById(adminId);
        if (user.isPresent() && user.get() instanceof Admin) {
            return (Admin) user.get();  // Hacer casting de User a Admin
        } else {
            throw new RuntimeException("Admin no encontrado con ID: " + adminId);
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        return List.of();
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



