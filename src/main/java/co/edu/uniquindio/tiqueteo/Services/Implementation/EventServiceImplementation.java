package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Dto.EventDto;
import co.edu.uniquindio.tiqueteo.Model.Event;
import co.edu.uniquindio.tiqueteo.Repositories.EventRepository;
import co.edu.uniquindio.tiqueteo.Services.iEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImplementation implements iEventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImplementation(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(EventDto event) {
        return null;
    }

    @Override
    public Event updateEvent(EventDto event) {
        return null;
    }

    @Override
    public Event deleteEvent(EventDto event) {
        return null;
    }

    @Override
    public Event getEventById(int id) {
        return null;
    }
}
