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
import java.nio.file.FileSystems;
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
    public void generateQR(String purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElse(null);
        if (purchase == null) {
            System.err.println("Compra no encontrada");
            return;
        }
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            String qrContent = "Compra ID: " + purchase.getId() + ", Evento ID: " + purchase.getEventId()
                    + ", Cantidad: " + purchase.getCant() + ", Precio Total: " + purchase.getTotalPrice();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 300, 300);

            String qrPath = "qr_codes/" + purchase.getId() + ".png";
            Path path = FileSystems.getDefault().getPath(qrPath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            System.out.println("QR generado exitosamente en: " + qrPath);
        } catch (Exception e) {
            System.err.println("Error generando el QR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void sendVerification(String mail) {
        emailService.sendPurchaseEmail(mail);
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

