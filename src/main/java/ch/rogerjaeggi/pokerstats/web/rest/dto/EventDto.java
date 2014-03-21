package ch.rogerjaeggi.pokerstats.web.rest.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDto {

    private String uuid;

    private String name;

    private String hostPlayerUuid;

    private long date;

    private long updated;

}
