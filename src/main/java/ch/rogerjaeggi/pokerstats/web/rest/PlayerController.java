package ch.rogerjaeggi.pokerstats.web.rest;

import ch.rogerjaeggi.pokerstats.domain.Player;
import ch.rogerjaeggi.pokerstats.repository.PlayerRepository;
import ch.rogerjaeggi.pokerstats.web.rest.dto.PlayerDto;
import ch.rogerjaeggi.pokerstats.web.rest.mapper.PlayerMapper;
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
public class PlayerController {

    private final Logger log = LoggerFactory.getLogger(PlayerController.class);

    @Inject
    private PlayerRepository playerRepository;

    @RequestMapping(value = "/1.0/player/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<PlayerDto> getAllPlayers(HttpServletResponse response) {
        log.debug("REST request to get all players");
        List<Player> players = playerRepository.getAll();
        if (players == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return PlayerMapper.toDto(players);
    }

    @RequestMapping(value = "/1.0/player/{playerUuid}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody PlayerDto getPlayer(@PathVariable String playerUuid, HttpServletResponse response) {
        log.debug("REST request to get player : '{}'", playerUuid);
        Player player = playerRepository.getByUuid(playerUuid);
        if (player == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        return PlayerMapper.toDto(player);
    }
}
