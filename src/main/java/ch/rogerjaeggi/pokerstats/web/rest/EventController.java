package ch.rogerjaeggi.pokerstats.web.rest;

import ch.rogerjaeggi.pokerstats.domain.Event;
import ch.rogerjaeggi.pokerstats.domain.Tournament;
import ch.rogerjaeggi.pokerstats.repository.*;
import ch.rogerjaeggi.pokerstats.web.rest.dto.EventDto;
import ch.rogerjaeggi.pokerstats.web.rest.mapper.EventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@Controller
public class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);

    @Inject
    private EventRepository eventRepository;

    @Inject
    private SeasonRepository seasonRepository;

    @Inject
    private VenueRepository venueRepository;

    @Inject
    private PlayerRepository playerRepository;

    @Inject
    private TournamentRepository tournamentRepository;

    @RequestMapping(value = "/1.0/event", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EventDto> getEvents(HttpServletResponse response) {
        log.debug("REST request to get all events");
        List<Event> events = eventRepository.getAll();
        if (events == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        List<EventDto> dtos = new LinkedList<>();
        for (Event event : events) {
            dtos.add(EventMapper.toDto(event, null));
        }
        return dtos;
    }

    @RequestMapping(value = "/1.0/event", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    EventDto addEvent(@RequestBody EventDto eventDto) {
        log.debug("REST request to add event, name=" + eventDto.getName());
        Event event = EventMapper.fromDto(eventDto);
        event.setSeason(seasonRepository.getByUuid("3"));
        event.setVenue(venueRepository.getByUuid(eventDto.getVenueUuid()));
        event.setHost(playerRepository.getByUuid(eventDto.getHostPlayerUuid()));
        eventRepository.update(event);
        return eventDto;
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

        // TODO Java 8 anyone?
        List<Tournament> tournaments = tournamentRepository.getByEventUuid(event.getUuid());
        List<String> tournamentUuids = new LinkedList<>();
        for (Tournament tournament : tournaments) {
            tournamentUuids.add(tournament.getUuid());
        }
        return EventMapper.toDto(event, tournamentUuids);
    }

}
