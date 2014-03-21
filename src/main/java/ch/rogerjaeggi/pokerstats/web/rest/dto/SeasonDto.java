package ch.rogerjaeggi.pokerstats.web.rest.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SeasonDto {

    private String uuid;

    private String name;

    private boolean current;

    private List<String> events;

}
