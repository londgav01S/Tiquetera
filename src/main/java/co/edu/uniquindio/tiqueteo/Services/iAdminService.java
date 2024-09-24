package co.edu.uniquindio.tiqueteo.Services;

import co.edu.uniquindio.tiqueteo.Dto.AdminDto;
import co.edu.uniquindio.tiqueteo.Dto.EventDto;
import co.edu.uniquindio.tiqueteo.Model.Admin;
import co.edu.uniquindio.tiqueteo.Model.Event;

import java.util.List;

public interface iAdminService {

    // Operaciones CRUD para Administradores usando AdminDto
    AdminDto createAdmin(AdminDto adminDto);
    AdminDto updateAdmin(AdminDto adminDto);
    void deleteAdmin(AdminDto adminDto);
    AdminDto getAdminById(String adminId);
    List<AdminDto> getAllAdmins();
    // Crear un nuevo evento
    Event createEvent(Event event);

    // Editar un evento existente
    Event updateEvent(String eventId, Event event);

    // Eliminar (marcar como inactivo) un evento
    void deleteEvent(String eventId);

    // Obtener todos los eventos (activos/inactivos)
    List<Event> getAllEvents();

    // Obtener un evento por su ID
    Event getEventById(String eventId);
}


