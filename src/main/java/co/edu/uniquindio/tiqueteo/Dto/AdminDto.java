package co.edu.uniquindio.tiqueteo.Dto;

import co.edu.uniquindio.tiqueteo.Model.Coupon;
import co.edu.uniquindio.tiqueteo.Model.Event;

import java.util.ArrayList;
import java.util.List;

public record AdminDto(
        List<CouponDto> couponList,
        List<EventDto> eventList
) {

}
