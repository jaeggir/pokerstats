package ch.rogerjaeggi.pokerstats.web.rest.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeasonDto {

    private String uuid;

    private String name;

    private boolean current;

}
