package co.edu.uniquindio.tiqueteo.Services;

import co.edu.uniquindio.tiqueteo.Model.Coupon;
import co.edu.uniquindio.tiqueteo.Model.Purchase;

public interface ICouponService {
    Coupon findCouponByCode(String couponCode);
    boolean applyCouponToPurchase(Purchase purchase, String couponCode);
}
