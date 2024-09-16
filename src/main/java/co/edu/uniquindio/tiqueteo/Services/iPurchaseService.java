package co.edu.uniquindio.tiqueteo.Services;

public interface iPurchaseService {
    public void generateQR();
    public void sendVerification(String mail);
    public void applyCoupon(String code);
}
