package ch.rogerjaeggi.pokerstats.web.rest.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentDto {

    private String uuid;

    private String eventUuid;

    private int round;

    private int status;

    private String resultSubmittedByPlayerUuid;

    private long updated;

}
