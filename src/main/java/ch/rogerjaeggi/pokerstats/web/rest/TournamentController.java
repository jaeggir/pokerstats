package ch.rogerjaeggi.pokerstats.web.rest;


import ch.rogerjaeggi.pokerstats.domain.Tournament;
import ch.rogerjaeggi.pokerstats.repository.TournamentRepository;
import ch.rogerjaeggi.pokerstats.web.rest.dto.TournamentDto;
import ch.rogerjaeggi.pokerstats.web.rest.mapper.TournamentMapper;
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

@Controller
public class TournamentController {

    private final Logger log = LoggerFactory.getLogger(TournamentController.class);

    @Inject
    private TournamentRepository tournamentRepository;

    @RequestMapping(value = "/1.0/tournament/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<TournamentDto> getAllTournaments(HttpServletResponse response) {
        log.debug("REST request to get all tournaments");
        List<Tournament> tournaments = tournamentRepository.getAll();
        if (tournaments == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return TournamentMapper.toDto(tournaments);
    }

    @RequestMapping(value = "/1.0/tournament/{tournamentUuid}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    TournamentDto getTournament(@PathVariable String tournamentUuid, HttpServletResponse response) {
        log.debug("REST request to get tournament : '{}'", tournamentUuid);
        Tournament tournament = tournamentRepository.getByUuid(tournamentUuid);
        if (tournament == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return TournamentMapper.toDto(tournament);
    }
}
