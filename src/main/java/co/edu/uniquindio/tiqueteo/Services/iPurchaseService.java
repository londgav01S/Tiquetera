package co.edu.uniquindio.tiqueteo.Services;

public interface iPurchaseService {
    public void generateQR(String purchaseId);
    public void sendVerification(String mail);
    public void applyCoupon(String purchaseId, String couponCode);
}
