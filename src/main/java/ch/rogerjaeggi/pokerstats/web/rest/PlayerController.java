package ch.rogerjaeggi.pokerstats.web.rest;

import ch.rogerjaeggi.pokerstats.domain.Player;
import ch.rogerjaeggi.pokerstats.domain.TournamentResult;
import ch.rogerjaeggi.pokerstats.repository.PlayerRepository;
import ch.rogerjaeggi.pokerstats.repository.TournamentResultRepository;
import ch.rogerjaeggi.pokerstats.web.rest.dto.PlayerDto;
import ch.rogerjaeggi.pokerstats.web.rest.dto.TournamentResultDto;
import ch.rogerjaeggi.pokerstats.web.rest.mapper.PlayerMapper;
import ch.rogerjaeggi.pokerstats.web.rest.mapper.TournamentResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static ch.rogerjaeggi.pokerstats.web.config.Constants.API_PREFIX;

@Controller
public class PlayerController {

    private final Logger log = LoggerFactory.getLogger(PlayerController.class);

    @Inject
    private PlayerRepository playerRepository;

    @Inject
    private TournamentResultRepository tournamentResultRepository;

    @RequestMapping(
            value = API_PREFIX + "player",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PlayerDto> getAllPlayers() {
        log.debug("REST request to get all players");
        List<Player> players = playerRepository.getAll();
        return PlayerMapper.toDto(players);
    }

    @RequestMapping(
            value = API_PREFIX + "player/{playerUuid}",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody PlayerDto getPlayer(@PathVariable String playerUuid, HttpServletResponse response) {
        log.debug("REST request to get player : '{}'", playerUuid);
        Player player = playerRepository.getByUuid(playerUuid);
        if (player == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        return PlayerMapper.toDto(player);
    }

    @RequestMapping(
            value = API_PREFIX + "player/{playerUuid}/results",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<TournamentResultDto> getResults(@PathVariable String playerUuid, HttpServletResponse response) {
        log.debug("REST request to get player : '{}'", playerUuid);
        List<TournamentResult> results = tournamentResultRepository.getAllForPlayer(playerUuid);
        if (results.isEmpty()) {
            // check if the player exists
            if (playerRepository.getByUuid(playerUuid) == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }
        }

        return TournamentResultMapper.toDto(results);
    }

    @RequestMapping(
            value = API_PREFIX + "player", method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public @ResponseBody PlayerDto addPlayer(@RequestBody PlayerDto playerDto) {
        log.debug("REST request to add player : '{}'", playerDto.getNickname());
        Player player = playerRepository.update(PlayerMapper.fromDto(playerDto));
        return PlayerMapper.toDto(player);
    }
}
