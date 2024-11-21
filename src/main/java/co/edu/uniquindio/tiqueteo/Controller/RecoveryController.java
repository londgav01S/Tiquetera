package co.edu.uniquindio.tiqueteo.Controller;

import co.edu.uniquindio.tiqueteo.Services.Implementation.ClientServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
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
        clientService.generateRecoveryCode(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate-code")
    public ResponseEntity<?> validateRecoveryCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        boolean isValid = clientService.validateRecoveryCode(email, code);
        return ResponseEntity.ok(Map.of("valid", isValid));
    }
}
