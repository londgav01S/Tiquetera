package co.edu.uniquindio.tiqueteo.Controller;

import co.edu.uniquindio.tiqueteo.Model.Client;
import co.edu.uniquindio.tiqueteo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/client")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard(@AuthenticationPrincipal OidcUser user, Model model) {
        if (user == null) {
            return ResponseEntity.badRequest().body("Usuario no autenticado.");
        }

        String email = user.getEmail();
        Client existingClient = userRepository.findByEmail(email);
        if (existingClient == null) {
            Client newClient = new Client();
            newClient.setName(user.getFullName());
            newClient.setEmail(email);
            userRepository.save(newClient);
        }

        model.addAttribute("name", user.getFullName());
        return ResponseEntity.ok("Bienvenido, " + user.getFullName());
    }

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal OidcUser user) {
        if (user != null) {
            return "redirect:/api/client/dashboard";
        }
        return "login"; // Vista de inicio de sesión si no está autenticado
    }
}


