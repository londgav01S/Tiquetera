package co.edu.uniquindio.tiqueteo.Controller;

import co.edu.uniquindio.tiqueteo.Model.Client;
import co.edu.uniquindio.tiqueteo.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/api/logear")
@CrossOrigin
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // Realizar pruebas para probar que se estan guardando los datos del usuario en la base de datos
    // Verificar direcciones de las vistas en el front y redirigir a la pestaña correcta
    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard(@AuthenticationPrincipal OidcUser user, Model model) {
        if (user == null) {
            return ResponseEntity.badRequest().body("User not authenticated");
        }
        String email = user.getEmail();
        Client existingClient = userRepository.findByEmail(email);
        if (existingClient == null) {
            Client newClient = new Client();
            newClient.setName(user.getFullName());
            newClient.setEmail(email);
            // Añadir datos a almacenar en la base de datos
            userRepository.save(newClient);
        }

        model.addAttribute("name", user.getFullName());
        return ResponseEntity.ok("Welcome, " + user.getFullName());
    }

    
    @GetMapping("/login")
    public ResponseEntity<?> login(Authentication authentication) {
        System.out.println("entrando a autenticacion");
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("redirect:/dashboard");
        }
        return ResponseEntity.badRequest().body("Ñao ñao");
    }


}
