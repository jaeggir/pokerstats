package ch.rogerjaeggi.pokerstats.web.rest;

import ch.rogerjaeggi.pokerstats.domain.Event;
import ch.rogerjaeggi.pokerstats.repository.EventRepository;
import ch.rogerjaeggi.pokerstats.web.rest.dto.EventDto;
import ch.rogerjaeggi.pokerstats.web.rest.mapper.EventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@Controller
public class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);

    @Inject
    private EventRepository eventRepository;

    @RequestMapping(value = "/1.0/event/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EventDto> getEvents(HttpServletResponse response) {
        log.debug("REST request to get events");
        List<Event> events = eventRepository.getAll();
        if (events == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        List<EventDto> dtos = new LinkedList<>();
        for (Event event : events) {
            dtos.add(EventMapper.toDto(event));
        }
        return dtos;
    }

    @RequestMapping(value = "/1.0/event/{uuid}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    EventDto getEvent(@PathVariable String uuid, HttpServletResponse response) {
        log.debug("REST request to get event : '{}'", uuid);
        Event event = eventRepository.getByUuid(uuid);
        if (event == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return EventMapper.toDto(event);
    }

}
