package ch.rogerjaeggi.pokerstats.web.rest;

import ch.rogerjaeggi.pokerstats.domain.Event;
import ch.rogerjaeggi.pokerstats.domain.Season;
import ch.rogerjaeggi.pokerstats.repository.EventRepository;
import ch.rogerjaeggi.pokerstats.repository.SeasonRepository;
import ch.rogerjaeggi.pokerstats.web.rest.dto.SeasonDto;
import ch.rogerjaeggi.pokerstats.web.rest.mapper.SeasonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

import static ch.rogerjaeggi.pokerstats.web.config.Constants.API_PREFIX;

@Controller
public class SeasonController {

    private final Logger log = LoggerFactory.getLogger(SeasonController.class);

    @Inject
    private SeasonRepository seasonRepository;

    @Inject
    private EventRepository eventRepository;

    @RequestMapping(value = API_PREFIX + "season", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<SeasonDto> getCurrentSeason(
            HttpServletResponse response,
            @RequestParam(value = "filter", required = false) String filter) {

        if ("current".equals(filter)) {
            log.debug("REST request to get the current season");
            Season season = seasonRepository.getCurrentSeason();
            if (season == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            LinkedList<SeasonDto> seasons = new LinkedList<>();
            SeasonDto dto = SeasonMapper.toDto(season, getEventUuids(season.getUuid()));
            seasons.add(dto);
            return seasons;
        } else {
            log.debug("REST request to get all seasons");
            List<Season> seasons = seasonRepository.getAll();
            if (seasons == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
            return SeasonMapper.toDto(seasons);
        }
    }

    @RequestMapping(value = API_PREFIX + "season/{seasonUuid}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody SeasonDto getSeason(@PathVariable String seasonUuid, HttpServletResponse response) {
        log.debug("REST request to get season : '{}'", seasonUuid);
        Season season = seasonRepository.getByUuid(seasonUuid);
        if (season == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return SeasonMapper.toDto(season, getEventUuids(seasonUuid));
    }

    private List<String> getEventUuids(String seasonUuid) {
        // TODO map, Java8?
        List<Event> events = eventRepository.getBySeason(seasonUuid);
        List<String> eventUuids = new LinkedList<>();
        for (Event event : events) {
            eventUuids.add(event.getUuid());
        }
        return eventUuids;
    }


}