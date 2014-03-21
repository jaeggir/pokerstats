package ch.rogerjaeggi.pokerstats.web.rest;


import ch.rogerjaeggi.pokerstats.domain.Tournament;
import ch.rogerjaeggi.pokerstats.domain.TournamentResult;
import ch.rogerjaeggi.pokerstats.repository.TournamentRepository;
import ch.rogerjaeggi.pokerstats.repository.TournamentResultRepository;
import ch.rogerjaeggi.pokerstats.web.rest.dto.TournamentDto;
import ch.rogerjaeggi.pokerstats.web.rest.dto.TournamentResultDto;
import ch.rogerjaeggi.pokerstats.web.rest.mapper.TournamentMapper;
import ch.rogerjaeggi.pokerstats.web.rest.mapper.TournamentResultMapper;
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
public class TournamentController {

    private final Logger log = LoggerFactory.getLogger(TournamentController.class);

    @Inject
    private TournamentRepository tournamentRepository;

    @Inject
    private TournamentResultRepository tournamentResultRepository;

    @RequestMapping(value = "/1.0/tournament", method = RequestMethod.GET, produces = "application/json")
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

        List<String> results = new LinkedList<>();
        for (TournamentResult result : tournamentResultRepository.getAllForTournament(tournamentUuid)) {
            results.add(result.getUuid());
        }

        TournamentDto dto = TournamentMapper.toDto(tournament);
        dto.setResults(results);
        return dto;
    }

    @RequestMapping(value = "/1.0/tournament/{tournamentUuid}/result", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<TournamentResultDto> getResults(@PathVariable String tournamentUuid, HttpServletResponse response) {
        log.debug("REST request to get tournament results: '{}'", tournamentUuid);
        List<TournamentResult> results = tournamentResultRepository.getAllForTournament(tournamentUuid);
        if (results == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return TournamentResultMapper.toDto(results);
    }
}
