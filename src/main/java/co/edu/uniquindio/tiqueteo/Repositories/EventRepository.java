package co.edu.uniquindio.tiqueteo.Repositories;


import co.edu.uniquindio.tiqueteo.Model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event,String> {
}
