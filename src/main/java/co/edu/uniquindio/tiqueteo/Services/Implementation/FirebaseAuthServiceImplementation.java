package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Services.IFirebaseAuthService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

@Service
public class FirebaseAuthServiceImplementation implements IFirebaseAuthService {
    public FirebaseToken verifyToken(String token) throws Exception {
        return FirebaseAuth.getInstance().verifyIdToken(token);
    }
}
