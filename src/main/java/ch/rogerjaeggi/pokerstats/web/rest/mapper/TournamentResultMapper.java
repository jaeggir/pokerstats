package ch.rogerjaeggi.pokerstats.web.rest.mapper;


import ch.rogerjaeggi.pokerstats.domain.TournamentResult;
import ch.rogerjaeggi.pokerstats.web.rest.dto.TournamentResultDto;

import java.util.LinkedList;
import java.util.List;

public class TournamentResultMapper {

    public static List<TournamentResultDto> toDto(List<TournamentResult> results) {
        List<TournamentResultDto> dtos = new LinkedList<>();
        for (TournamentResult result : results) {
            dtos.add(toDto(result));
        }
        return dtos;
    }

    public static TournamentResultDto toDto(TournamentResult result) {
        TournamentResultDto dto = new TournamentResultDto();
        dto.setUuid(result.getUuid());
        dto.setUpdated(result.getUpdated().toDateTime().getMillis());
        dto.setEliminatedByPlayerUuid(result.getEliminatedByPlayerUuid());
        dto.setPlayerUuid(result.getPlayerUuid());
        dto.setRank(result.getRank());
        dto.setTournamentUuid(result.getTournamentUuid());
        dto.setAnte(result.getAnte());
        dto.setWin(result.getWin());
        return dto;
    }
}
