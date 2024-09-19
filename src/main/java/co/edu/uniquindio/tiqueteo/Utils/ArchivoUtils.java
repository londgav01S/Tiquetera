package co.edu.uniquindio.tiqueteo.Utils;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class ArchivoUtils {

    private String smtpHost = "smtp.example.com"; // Cambia esto por tu servidor SMTP
    private String smtpPort = "587"; // Puerto SMTP (587 para TLS, 465 para SSL)
    private String fromEmail = "infotiqueteo@gmail.com"; // Cambia por tu dirección de correo electrónico
    private String password = "tiqueteo123*"; // Cambia por tu contraseña de correo

    public void enviarCorreo(String mensaje, String correoDestino) {
        // Configuración de las propiedades para la conexión SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        // Autenticación con el servidor SMTP
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Crear el mensaje de correo
            Message correo = new MimeMessage(session);
            correo.setFrom(new InternetAddress(fromEmail));
            correo.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));
            correo.setSubject("Asunto del Correo");
            correo.setText(mensaje);

            // Enviar el correo
            Transport.send(correo);
            System.out.println("Correo enviado exitosamente!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Método que permite cambiar el servidor SMTP y puerto si es necesario
    public void setSmtpConfig(String smtpHost, String smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    // Método que permite cambiar el correo y contraseña del remitente
    public void setSenderCredentials(String fromEmail, String password) {
        this.fromEmail = fromEmail;
        this.password = password;
    }
}

