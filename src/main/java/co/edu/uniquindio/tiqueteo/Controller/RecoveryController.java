package co.edu.uniquindio.tiqueteo.Controller;

import co.edu.uniquindio.tiqueteo.Services.Implementation.ClientServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RecoveryController {

    @Autowired
    private ClientServiceImplementation clientService;

    @PostMapping("/recovery-code")
    public ResponseEntity<?> requestRecoveryCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        System.out.println("entrando a recuperar contraseña: "+email);
        clientService.generateRecoveryCode(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate-code")
    public ResponseEntity<?> validateRecoveryCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        System.out.println("validando código de recuperación: "+email+", "+code);
        boolean isValid = clientService.validateRecoveryCode(email, code);
        return ResponseEntity.ok(Map.of("valid", isValid));
    }

    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");
        System.out.println("Actualizando contraseña para: " + email);

        boolean isUpdated = clientService.updatePassword(email, newPassword);
        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
}
