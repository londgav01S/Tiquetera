package co.edu.uniquindio.tiqueteo.Services;

import java.io.File;

public interface iPurchaseService {
    public File generateQR(String purchaseId);
    public void sendVerification(String mail, String purchaseId);
    public void applyCoupon(String purchaseId, String couponCode);
}
