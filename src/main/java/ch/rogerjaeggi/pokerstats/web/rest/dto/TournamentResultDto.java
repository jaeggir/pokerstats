package ch.rogerjaeggi.pokerstats.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TournamentResultDto {

    private String uuid;

    private String tournamentUuid;

    private String playerUuid;

    private Integer rank;

    private Integer points;

    private String eliminatedByPlayerUuid;

    private long updated;

    private Integer ante;

    private Integer win;
}
