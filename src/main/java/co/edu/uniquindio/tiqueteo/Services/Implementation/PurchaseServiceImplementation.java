package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Model.Purchase;
import co.edu.uniquindio.tiqueteo.Repositories.PurchaseRepository;
import co.edu.uniquindio.tiqueteo.Services.ICouponService;
import co.edu.uniquindio.tiqueteo.Services.IEmailService;
import co.edu.uniquindio.tiqueteo.Services.iPurchaseService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PurchaseServiceImplementation implements iPurchaseService {

    @Autowired
    private IEmailService emailService;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ICouponService couponService;

    @Override
    public File generateQR(String purchaseId) {
        // Buscar la compra en el repositorio
        Purchase purchase = purchaseRepository.findById(purchaseId).orElse(null);
        if (purchase == null) {
            System.err.println("Compra no encontrada");
            return null;
        }
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            // Contenido del QR
            String qrContent = "Compra ID: " + purchase.getId() + ", Evento ID: " + purchase.getEventId()
                    + ", Cantidad: " + purchase.getCant() + ", Precio Total: " + purchase.getTotalPrice();
            // Generar el QR como matriz de bits
            BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 300, 300);
            // Crear archivo temporal para guardar el QR
            File tempFile = File.createTempFile("QR_" + purchase.getId(), ".png");
            // Escribir la matriz de bits en el archivo
            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            }
            System.out.println("QR generado exitosamente en: " + tempFile.getAbsolutePath());
            return tempFile; // Retornar el archivo generado
        } catch (Exception e) {
            System.err.println("Error generando el QR: " + e.getMessage());
            e.printStackTrace();
        }

        return null; // Retornar null si ocurre algún error
    }


    @Override
    public void sendVerification(String mail, String purchaseId) {
        emailService.sendPurchaseEmail(mail, generateQR(purchaseId));
        System.out.println("Correo de confirmación de compra enviado a: " + mail);
    }

    @Override
    public void applyCoupon(String purchaseId, String couponCode) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElse(null);
        if (purchase == null) {
            System.err.println("Compra no encontrada");
            return;
        }

        boolean success = couponService.applyCouponToPurchase(purchase, couponCode);
        if (success) {
            purchaseRepository.save(purchase);
            System.out.println("Compra actualizada con el descuento del cupón.");
        } else {
            System.err.println("No se pudo aplicar el cupón a la compra.");
        }
    }
}

