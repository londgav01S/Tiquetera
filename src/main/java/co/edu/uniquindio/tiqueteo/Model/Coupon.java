package co.edu.uniquindio.tiqueteo.Model;

import org.springframework.data.annotation.Id;

public class Coupon {

    @Id
    String id;
    String code;
    String name;
    Double percentage;
    String date;


}
