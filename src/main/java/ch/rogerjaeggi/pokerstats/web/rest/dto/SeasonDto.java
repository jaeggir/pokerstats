package ch.rogerjaeggi.pokerstats.web.rest.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeasonDto {

    private String uuid;

    private String name;

    private Boolean current;

    private List<String> events;

}
