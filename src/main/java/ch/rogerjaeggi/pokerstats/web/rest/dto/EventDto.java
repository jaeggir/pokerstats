package ch.rogerjaeggi.pokerstats.web.rest.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDto {

    private String uuid;

    private String hostPlayerUuid;

    private String venueUuid;

    private String name;

    private long date;

    private long updated;

    private List<String> tournaments;

}
