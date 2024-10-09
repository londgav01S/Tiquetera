package co.edu.uniquindio.tiqueteo.Services;

import co.edu.uniquindio.tiqueteo.Dto.EventDto;
import co.edu.uniquindio.tiqueteo.Dto.RangeDateDto;
import co.edu.uniquindio.tiqueteo.Dto.ReportDto;
import co.edu.uniquindio.tiqueteo.Dto.UserDto;
import co.edu.uniquindio.tiqueteo.Model.Event;

import java.time.LocalDate;
import java.util.List;

public interface iAdminService {

    ReportDto generateReport (RangeDateDto rangeDateDto);
    //ReportDto generateReport (String startDate, String endDate);

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


