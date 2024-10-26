package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

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

    public void sendEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tiqueteo@gmail.com");
        message.setTo(toEmail);
        message.setSubject(emailSubject);
        message.setText(emailBody);
        mailSender.send(message);
    }

    public void sendPurchaseEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tiqueteo@gmail.com");
        message.setTo(toEmail);
        message.setSubject(purchaseSubject);
        message.setText(purchaseBody);
        mailSender.send(message);
    }
}

