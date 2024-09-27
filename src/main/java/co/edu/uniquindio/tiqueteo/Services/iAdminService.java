package co.edu.uniquindio.tiqueteo.Services;

import co.edu.uniquindio.tiqueteo.Dto.EventDto;
import co.edu.uniquindio.tiqueteo.Dto.UserDto;
import co.edu.uniquindio.tiqueteo.Model.Event;

import java.util.List;

public interface iAdminService {

    // Operaciones CRUD para Administradores usando AdminDto
    UserDto createAdmin(UserDto userDto);
    UserDto updateAdmin(UserDto userDto);
    void deleteAdmin(UserDto userDto);
    UserDto getAdminById(String adminId);
    List<UserDto> getAllAdmins();

    EventDto createEvent(EventDto eventdto);
    EventDto updateEvent(EventDto eventdto);
    void deleteEvent(EventDto eventdto);
    EventDto getEventById(String id);
    List<EventDto> getAllEvents();

}


