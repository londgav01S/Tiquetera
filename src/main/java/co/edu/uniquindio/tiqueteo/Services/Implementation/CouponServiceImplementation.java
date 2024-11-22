package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Dto.CouponDto;
import co.edu.uniquindio.tiqueteo.Model.Coupon;
import co.edu.uniquindio.tiqueteo.Model.Purchase;
import co.edu.uniquindio.tiqueteo.Repositories.CouponRepository;
import co.edu.uniquindio.tiqueteo.Services.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class CouponServiceImplementation implements ICouponService {

    @Autowired
    private CouponRepository couponRepository;

    private CouponDto ToCouponDto(Coupon coupon) {
        return new CouponDto(coupon.getId(), coupon.getCode(), coupon.getName(), coupon.getPercentage(), coupon.getDate());
    }

    private Coupon toEntity(CouponDto couponDto) {
        Coupon coupon = new Coupon();
        coupon.setId(couponDto.getId());
        coupon.setCode(couponDto.getCode());
        coupon.setName(couponDto.getName());
        coupon.setPercentage(couponDto.getPercentage());
        coupon.setDate(couponDto.getDate());
        return coupon;
    }

    @Override
    public CouponDto createCoupon(CouponDto couponDto) {
        Coupon coupon = toEntity(couponDto);
        Coupon couponSaved= couponRepository.save(coupon);
        return ToCouponDto(couponSaved);
    }

    @Override
    public Coupon findCouponByCode(String couponCode) {
        return couponRepository.findByCode(couponCode).orElse(null);
    }

    @Override
    public boolean applyCouponToPurchase(Purchase purchase, String couponCode) {
        Coupon coupon = findCouponByCode(couponCode);
        if (coupon == null) {
            System.err.println("Cupón no encontrado");
            return false;
        }
        try {
            LocalDate expirationDate = LocalDate.parse(coupon.getDate(), DateTimeFormatter.ISO_DATE);
            if (expirationDate.isBefore(LocalDate.now())) {
                System.err.println("El cupón ha expirado");
                return false;
            }
        } catch (DateTimeParseException e) {
            System.err.println("Formato de fecha inválido en el cupón");
            return false;
        }
        double discount = coupon.getPercentage();
        double originalPrice = purchase.getTotalPrice();
        double discountedPrice = originalPrice - (originalPrice * (discount / 100));

        purchase.setTotalPrice(discountedPrice);

        System.out.println("Cupón aplicado exitosamente. Nuevo precio: " + discountedPrice);
        return true;
    }

}
