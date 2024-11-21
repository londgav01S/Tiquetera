package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Services.IEmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;


@Service
public class EmailServiceImplementation implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${email.registration.subject}")
    private String emailSubject;

    @Value("${email.registration.body}")
    private String emailBody;

    @Value("${email.purchase.subject}")
    private String purchaseSubject;

    @Value("${email.purchase.body}")
    private String purchaseBody;

    @Override
    public void sendEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@tiqueteo.com"); // Cambia el remitente si es necesario
        message.setTo(toEmail);
        message.setSubject(emailSubject);
        message.setText(emailBody);
        mailSender.send(message);
    }

    @Override
    public void sendEmailWithBody(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@tiqueteo.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Override
    public void sendPurchaseEmail(String toEmail, File qrFile) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("no-reply@tiqueteo.com");
            helper.setTo(toEmail);
            helper.setSubject(purchaseSubject);
            helper.setText(purchaseBody);

            // Adjuntar el archivo QR
            helper.addAttachment("QR_Code.png", qrFile);

            mailSender.send(message);
            System.out.println("Correo enviado con el QR adjunto a: " + toEmail);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


