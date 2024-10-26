package co.edu.uniquindio.tiqueteo.Services;

import com.google.firebase.auth.FirebaseToken;

public interface IFirebaseAuthService {
    public FirebaseToken verifyToken(String token) throws Exception;
}
