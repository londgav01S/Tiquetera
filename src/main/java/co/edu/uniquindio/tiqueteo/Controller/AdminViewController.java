package co.edu.uniquindio.tiqueteo.Controller;

import co.edu.uniquindio.tiqueteo.Dto.AdminDto;
import co.edu.uniquindio.tiqueteo.Model.Admin;
import co.edu.uniquindio.tiqueteo.Services.iAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")  // Ruta base para Admin
public class AdminViewController {

    @Autowired
    private iAdminService adminService;

    // Crear un nuevo admin: POST /api/admin
    @PostMapping
    public AdminDto create(@RequestBody AdminDto adminDto) {
        return adminService.createAdmin(adminDto);
    }

    // Actualizar un admin: PUT /api/admin
    @PutMapping
    public AdminDto update(@RequestBody AdminDto adminDto) {
        return adminService.updateAdmin(adminDto);
    }

    // Eliminar un admin por ID: DELETE /api/admin/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        AdminDto adminDelete = adminService.getAdminById(id);
        adminService.deleteAdmin(adminDelete);
    }

    // Obtener un admin por ID: GET /api/admin/{id}
    @GetMapping("/{id}")
    public AdminDto getAdminById(@PathVariable String id) {
        return adminService.getAdminById(id);
    }

    // Obtener todos los admins: GET /api/admin/all
    @GetMapping("/all")
    public List<AdminDto> getAllAdmins() {
        return adminService.getAllAdmins();
    }
}