package ch.rogerjaeggi.pokerstats.web.rest;

import ch.rogerjaeggi.pokerstats.domain.Venue;
import ch.rogerjaeggi.pokerstats.repository.VenueRepository;
import ch.rogerjaeggi.pokerstats.web.rest.dto.VenueDto;
import ch.rogerjaeggi.pokerstats.web.rest.mapper.VenueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static ch.rogerjaeggi.pokerstats.web.config.Constants.API_PREFIX;

@Controller
public class VenueController {

    private final Logger log = LoggerFactory.getLogger(VenueController.class);

    @Inject
    private VenueRepository venueRepository;

    @RequestMapping(value = API_PREFIX + "venue", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<VenueDto> getAllVenues() {
        log.debug("REST request to get all venues");
        List<Venue> venues = venueRepository.getAll();
        return VenueMapper.toDto(venues);
    }

    @RequestMapping(value = API_PREFIX + "venue/{venueUuid}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    VenueDto getVenue(@PathVariable String venueUuid, HttpServletResponse response) {
        log.debug("REST request to get venue : '{}'", venueUuid);
        Venue venue = venueRepository.getByUuid(venueUuid);
        if (venue == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return VenueMapper.toDto(venue);
    }

}
