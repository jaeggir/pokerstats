package ch.rogerjaeggi.pokerstats.web.rest.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventDto {

    private String uuid;

    private String name;

    private String hostPlayerUuid;

    private long date;

    private long updated;

    private List<String> tournaments;

}
