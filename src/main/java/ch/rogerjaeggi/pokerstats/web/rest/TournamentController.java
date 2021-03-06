package ch.rogerjaeggi.pokerstats.web.rest;


import ch.rogerjaeggi.pokerstats.domain.Tournament;
import ch.rogerjaeggi.pokerstats.domain.TournamentResult;
import ch.rogerjaeggi.pokerstats.repository.TournamentRepository;
import ch.rogerjaeggi.pokerstats.repository.TournamentResultRepository;
import ch.rogerjaeggi.pokerstats.service.TournamentService;
import ch.rogerjaeggi.pokerstats.web.rest.dto.TournamentDto;
import ch.rogerjaeggi.pokerstats.web.rest.dto.TournamentResultDto;
import ch.rogerjaeggi.pokerstats.web.rest.mapper.TournamentMapper;
import ch.rogerjaeggi.pokerstats.web.rest.mapper.TournamentResultMapper;
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
public class TournamentController {

    private final Logger log = LoggerFactory.getLogger(TournamentController.class);

    @Inject
    private TournamentRepository tournamentRepository;

    @Inject
    private TournamentService tournamentService;

    @Inject
    private TournamentResultRepository tournamentResultRepository;

    @RequestMapping(
            value = API_PREFIX + "tournament",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<TournamentDto> getAllTournaments(@RequestParam(value = "eventUuid", required = false) String eventUuid) {

        List<Tournament> tournaments;
        if (eventUuid != null) {
            log.debug("REST request to get all tournaments with eventUuid='{}'", eventUuid);
            tournaments = tournamentRepository.getByEventUuid(eventUuid);
        } else {
            log.debug("REST request to get all tournaments");
            tournaments = tournamentRepository.getAll();
        }

        return TournamentMapper.toDto(tournaments);
    }

    @RequestMapping(
            value = API_PREFIX + "tournament/{tournamentUuid}",
            method = RequestMethod.GET, produces = "application/json")
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

    @RequestMapping(
            value = API_PREFIX + "tournament/{tournamentUuid}/results",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<TournamentResultDto> getResults(@PathVariable String tournamentUuid, HttpServletResponse response) {
        log.debug("REST request to get tournament results: '{}'", tournamentUuid);
        List<TournamentResult> results = tournamentResultRepository.getAllForTournament(tournamentUuid);
        if (results.isEmpty()) {
            // check if tournament exists
            if (tournamentRepository.getByUuid(tournamentUuid) == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
        }
        return TournamentResultMapper.toDto(results);
    }

    @RequestMapping(
            value = API_PREFIX + "tournament",
            method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody TournamentDto startTournament(@RequestBody TournamentDto tournamentDto) {
        log.debug("REST request to create tournament for event: '{}'", tournamentDto.getEventUuid());
        Tournament tournament = tournamentService.createTournament(tournamentDto);
        return TournamentMapper.toDto(tournament);

    }

    @RequestMapping(
            value = API_PREFIX + "tournament/{tournamentUuid}/result/{resultUuid}",
            method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public @ResponseBody TournamentResultDto eliminatePlayer(@PathVariable String tournamentUuid,
                                                             @PathVariable String resultUuid,
                                                             @RequestBody TournamentResultDto dto) {

        log.debug("REST request to eliminate player result '{}' for event '{}'", resultUuid, tournamentUuid);
        if (!tournamentUuid.equals(dto.getTournamentUuid()) || !resultUuid.equals(dto.getUuid())) {
            throw new RuntimeException("tournamentUuid and/or resultUuid did not match with values in body.");
        }
        TournamentResult result = tournamentService.updateResult(dto);
        return TournamentResultMapper.toDto(result);
    }

    @RequestMapping(
            value = API_PREFIX + "tournament/{tournamentUuid}",
            method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody TournamentDto endTournament(@PathVariable String tournamentUuid) {

        log.debug("REST request to end tournament '{}' for event '{}'", tournamentUuid);
        Tournament result = tournamentService.endTournament(tournamentUuid);
        return TournamentMapper.toDto(result);
    }
}
