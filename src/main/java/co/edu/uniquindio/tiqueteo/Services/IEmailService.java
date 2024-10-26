package co.edu.uniquindio.tiqueteo.Services;

public interface IEmailService {
    void sendEmail(String toEmail);
    void sendPurchaseEmail(String toEmail);
}
