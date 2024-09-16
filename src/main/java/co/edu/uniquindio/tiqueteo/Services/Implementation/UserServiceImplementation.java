package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Repositories.UserRepository;
import co.edu.uniquindio.tiqueteo.Services.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements iUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser() {

    }

    @Override
    public void loginUser() {

    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void sendVerification(String mail) {

    }

    @Override
    public void applyCoupon(String code) {

    }
}
