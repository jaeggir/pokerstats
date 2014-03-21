package ch.rogerjaeggi.pokerstats.web.rest.mapper;


import ch.rogerjaeggi.pokerstats.domain.Season;
import ch.rogerjaeggi.pokerstats.web.rest.dto.SeasonDto;

import java.util.LinkedList;
import java.util.List;

public class SeasonMapper {

    public static List<SeasonDto> toDto(List<Season> seasons) {
        LinkedList<SeasonDto> dtos = new LinkedList<>();
        for (Season season : seasons) {
            dtos.add(toDto(season, null));
        }
        return dtos;
    }

    public static List<SeasonDto> toDto(Season season) {
        LinkedList<SeasonDto> dtos = new LinkedList<>();
        dtos.add(toDto(season, null));
        return dtos;
    }

    public static SeasonDto toDto(Season season, List<String> events) {
        SeasonDto dto = new SeasonDto();
        dto.setUuid(season.getUuid());
        dto.setName(season.getName());
        dto.setCurrent(season.getCurrent());
        dto.setEvents(events);
        return dto;
    }
}
