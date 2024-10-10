package co.edu.uniquindio.tiqueteo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/logear")
@CrossOrigin
public class LoginController {


    @GetMapping("/login")
    public ResponseEntity<?> login(Authentication authentication) {
        System.out.println("entrando a autenticacion");
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("redirect:/dashboard");
        }
        return ResponseEntity.badRequest().body("Ñao ñao");
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal OidcUser user, Model model) {
        model.addAttribute("name", user.getFullName());
        return "dashboard";
    }
}
