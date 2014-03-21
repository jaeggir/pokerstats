package ch.rogerjaeggi.pokerstats.web.rest.mapper;

import ch.rogerjaeggi.pokerstats.domain.Tournament;
import ch.rogerjaeggi.pokerstats.web.rest.dto.TournamentDto;

import java.util.LinkedList;
import java.util.List;

public class TournamentMapper {

    public static List<TournamentDto> toDto(List<Tournament> tournaments) {
        List<TournamentDto> dtos = new LinkedList<>();
        for (Tournament tournament : tournaments) {
            dtos.add(toDto(tournament));
        }
        return dtos;
    }

    public static TournamentDto toDto(Tournament tournament) {
        TournamentDto dto = new TournamentDto();
        dto.setUuid(tournament.getUuid());
        dto.setEventUuid(tournament.getEventUuid());
        dto.setRound(tournament.getRound());
        dto.setStatus(tournament.getStatus());
        dto.setResultSubmittedByPlayerUuid(tournament.getResultSubmittedByPlayerUuid());
        dto.setUpdated(tournament.getUpdated().toDateTime().getMillis());
        return dto;
    }

}
