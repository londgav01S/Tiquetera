package co.edu.uniquindio.tiqueteo.Repositories;

import co.edu.uniquindio.tiqueteo.Model.Event;
import co.edu.uniquindio.tiqueteo.Model.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PurchaseRepository extends MongoRepository<Purchase,String> {
    // Encuentra todas las compras de un cliente por su ID
    List<Purchase> findByClientId(String clientId);

}
