package co.edu.uniquindio.tiqueteo.Services;

import co.edu.uniquindio.tiqueteo.Dto.EventDto;
import co.edu.uniquindio.tiqueteo.Model.Event;

public interface iAdminService {

    public Event createEvent(EventDto event);
    public Event updateEvent(EventDto event);
    public void deleteEvent(Event event);
    public Event getEventById(int id);

}
