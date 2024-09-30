package co.edu.uniquindio.tiqueteo.Controller;

import co.edu.uniquindio.tiqueteo.Dto.EventDto;
import co.edu.uniquindio.tiqueteo.Dto.RangeDateDto;
import co.edu.uniquindio.tiqueteo.Dto.ReportDto;
import co.edu.uniquindio.tiqueteo.Dto.UserDto;
import co.edu.uniquindio.tiqueteo.Services.iAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/admin")  // Ruta base para Admin

public class AdminViewController {

    @Autowired
    private iAdminService adminService;


    @GetMapping("/report")
    public ReportDto generateReport(@RequestBody RangeDateDto rangeDateDto) {
        LocalDate startDate = rangeDateDto.getStartDate();
        LocalDate endDate = rangeDateDto.getEndDate();
        // Lógica para generar reportes usando startDate y endDate
        return adminService.generateReport(rangeDateDto);
    }

        // Crear un nuevo admin: POST /api/admin
    @PostMapping("/register")
    public UserDto create(@RequestBody UserDto userDto) {
        return adminService.createAdmin(userDto);
    }

    // Actualizar un admin: PUT /api/admin
    @PutMapping("/updateAdmin")
    public UserDto update(@RequestBody UserDto userDto) {
        return adminService.updateAdmin(userDto);
    }

    // Eliminar un admin por ID: DELETE /api/admin/{id}
    @DeleteMapping("/{id}/deleteAdmin")
    public void delete(@PathVariable String id) {
        UserDto adminDelete = adminService.getAdminById(id);
        adminService.deleteAdmin(adminDelete);
    }

    // Obtener un admin por ID: GET /api/admin/{id}
    @GetMapping("/{id}/getAdmin")
    public UserDto getAdminById(@PathVariable String id) {
        return adminService.getAdminById(id);
    }

    // Obtener todos los admins: GET /api/admin/all
    @GetMapping("/allAdmins")
    public List<UserDto> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    //////////////////////////////////////////
    // Crear un nuevo evento
    @PostMapping("/createEvent")
    public EventDto create(@RequestBody EventDto eventDto) {
        return adminService.createEvent(eventDto);
    }

    // Actualizar un evento
    @PutMapping("/updateEvent")
    public EventDto updateEvent(@RequestBody EventDto eventDto) {
        return adminService.updateEvent(eventDto);
    }

    // Eliminar un evento por ID
    @DeleteMapping("/{id}/deleteEvent")
    public void deleteEvent(@PathVariable String id) {
        EventDto eventDelete = adminService.getEventById(id);
        adminService.deleteEvent(eventDelete);
    }

    // Obtener un evento por ID
    @GetMapping("/{id}/getEvent")
    public EventDto getEventById(@PathVariable String id) {
        return adminService.getEventById(id);
    }

    // Obtener todos los eventos
    @GetMapping("/allEvents")
    public List<EventDto> getAllEvents() {
        return adminService.getAllEvents();
    }


}