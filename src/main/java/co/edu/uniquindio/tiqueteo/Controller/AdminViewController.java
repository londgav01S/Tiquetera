package co.edu.uniquindio.tiqueteo.Controller;

import co.edu.uniquindio.tiqueteo.Model.Admin;
import co.edu.uniquindio.tiqueteo.Services.iAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")  // Ruta base para Admin
public class AdminViewController {

    @Autowired
    private iAdminService adminService;

    // Crear un nuevo admin: POST /api/admin
    @PostMapping
    public Admin create(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    // Actualizar un admin: PUT /api/admin
    @PutMapping
    public Admin update(@RequestBody Admin admin) {
        return adminService.updateAdmin(admin);
    }

    // Eliminar un admin por ID: DELETE /api/admin/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        Admin adminDelete = adminService.getAdminById(id);
        adminService.deleteAdmin(adminDelete);
    }

    // Obtener un admin por ID: GET /api/admin/{id}
    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable String id) {
        return adminService.getAdminById(id);
    }
}