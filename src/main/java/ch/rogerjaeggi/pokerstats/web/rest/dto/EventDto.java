package ch.rogerjaeggi.pokerstats.web.rest.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDto {

    private String uuid;

    private String name;

    private long date;

    private long updated;

}
