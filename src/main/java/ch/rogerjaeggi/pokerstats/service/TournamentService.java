package ch.rogerjaeggi.pokerstats.service;

import ch.rogerjaeggi.pokerstats.domain.*;
import ch.rogerjaeggi.pokerstats.repository.EventRepository;
import ch.rogerjaeggi.pokerstats.repository.PlayerRepository;
import ch.rogerjaeggi.pokerstats.repository.TournamentRepository;
import ch.rogerjaeggi.pokerstats.repository.TournamentResultRepository;
import ch.rogerjaeggi.pokerstats.web.rest.dto.TournamentDto;
import ch.rogerjaeggi.pokerstats.web.rest.dto.TournamentResultDto;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class TournamentService {

    @Inject
    private TournamentRepository tournamentRepository;

    @Inject
    private EventRepository eventRepository;

    @Inject
    private PlayerRepository playerRepository;

    @Inject
    private TournamentResultRepository tournamentResultRepository;

    public Tournament createTournament(TournamentDto dto) {
        Tournament tournament = new Tournament();
        tournament.setStatus(TournamentStatus.RUNNING.getId());
        tournament.setRound(getCurrentRound(dto.getEventUuid()));
        Event event = eventRepository.getByUuid(dto.getEventUuid());
        if (event == null) {
            throw new RuntimeException("Unknown event uuid: '" + dto.getEventUuid() + "'.");
        }
        tournament.setEvent(event);
        tournament = tournamentRepository.update(tournament);

        for (String playerUuid : dto.getResults()) {
            TournamentResult result = new TournamentResult();
            result.setTournament(tournament);
            result.setAnte(dto.getAnte());
            Player player = playerRepository.getByUuid(playerUuid);
            if (player == null) {
                throw new RuntimeException("Unknown player uuid: '" + playerUuid + "'.");
            }
            result.setPlayer(player);
            tournamentResultRepository.update(result);
        }

        return tournament;
    }

    private Integer getCurrentRound(String eventUuid) {
        List<Tournament> tournaments = tournamentRepository.getByEventUuid(eventUuid);
        int round = 0;
        for (Tournament tournament : tournaments) {
            if (tournament.getRound() > round) {
                round = tournament.getRound();
            }
        }
        return round + 1;
    }

    public TournamentResult updateResult(TournamentResultDto dto) {
        TournamentResult result = tournamentResultRepository.getByUuid(dto.getUuid());
        if (dto.getEliminatedByPlayerUuid() != null) {
            Player player = playerRepository.getByUuid(dto.getEliminatedByPlayerUuid());
            if (player == null) {
                throw new RuntimeException("Unknown player uuid: '" + dto.getEliminatedByPlayerUuid() + "'.");
            }
            result.setEliminatedBy(player);
        }
        result.setWin(dto.getWin());
        result.setRank(dto.getRank());
        result = tournamentResultRepository.update(result);
        return result;
    }

    public Tournament endTournament(String tournamentUuid) {
        Tournament tournament = tournamentRepository.getByUuid(tournamentUuid);
        if (tournament == null) {
            throw new RuntimeException("Unknown tournament uuid: '" + tournamentUuid + "'.");
        }
        tournament.setStatus(TournamentStatus.FINISHED.getId());
        return tournamentRepository.update(tournament);
    }
}
