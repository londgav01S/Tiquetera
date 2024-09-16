package co.edu.uniquindio.tiqueteo.Services;

public interface iUserService {
    public void registerUser();
    public void loginUser();
    public void updateUser();
    public void deleteUser();
    public void sendVerification(String mail);
    public void applyCoupon(String code);
}
