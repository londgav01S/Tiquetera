package co.edu.uniquindio.tiqueteo.Repositories;

import co.edu.uniquindio.tiqueteo.Model.Coupon;
import co.edu.uniquindio.tiqueteo.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends MongoRepository<Coupon,String> {

}
