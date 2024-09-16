package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Dto.EventDto;
import co.edu.uniquindio.tiqueteo.Model.Event;
import co.edu.uniquindio.tiqueteo.Services.iAdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImplementation implements iAdminService {
    @Override
    public Event createEvent(EventDto event) {
        return null;
    }

    @Override
    public Event updateEvent(EventDto event) {
        return null;
    }

    @Override
    public void deleteEvent(Event event) {

    }

    @Override
    public Event getEventById(int id) {
        return null;
    }
}
