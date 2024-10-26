package co.edu.uniquindio.tiqueteo.Controller;

import co.edu.uniquindio.tiqueteo.Services.Implementation.FirebaseAuthServiceImplementation;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final FirebaseAuthServiceImplementation firebaseAuthService;

    @Autowired
    public AuthController(FirebaseAuthServiceImplementation firebaseAuthService) {
        this.firebaseAuthService = firebaseAuthService;
    }

    //Verificar la direccion de la peticion del token
    @PostMapping("/api/verifyToken")
    public String verifyToken(@RequestHeader("Authorization") String token) {
        try {
            FirebaseToken decodedToken = firebaseAuthService.verifyToken(token.replace("Bearer ", ""));
            return "Token válido para el usuario: " + decodedToken.getUid();
        } catch (Exception e) {
            return "Token inválido";
        }
    }
}
