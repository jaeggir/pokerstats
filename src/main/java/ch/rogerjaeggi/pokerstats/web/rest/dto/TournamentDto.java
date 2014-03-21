package ch.rogerjaeggi.pokerstats.web.rest.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TournamentDto {

    private String uuid;

    private String eventUuid;

    private int round;

    private int status;

    private String resultSubmittedByPlayerUuid;

    private long updated;

}
