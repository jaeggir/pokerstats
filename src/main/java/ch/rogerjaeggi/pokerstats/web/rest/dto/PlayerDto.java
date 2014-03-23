package ch.rogerjaeggi.pokerstats.web.rest.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerDto {

    private String uuid;

    private String nickname;

    private Boolean guest;

    private long birthday;

    private long joined;

    private long updated;
}
