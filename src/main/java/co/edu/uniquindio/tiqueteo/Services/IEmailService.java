package co.edu.uniquindio.tiqueteo.Services;

import java.io.File;

public interface IEmailService {
    void sendRecoveryEmail(String toEmail, String subject, String body);
    void sendEmail(String toEmail);
    void sendPurchaseEmail(String toEmail, File qrFile);
}
