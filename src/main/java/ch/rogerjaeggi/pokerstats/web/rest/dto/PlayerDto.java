package ch.rogerjaeggi.pokerstats.web.rest.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDto {

    private String uuid;

    private String nickname;

    private boolean guest;

    private long birthday;

    private long joined;

    private long updated;
}
