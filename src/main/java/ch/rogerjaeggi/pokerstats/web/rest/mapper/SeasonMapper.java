package ch.rogerjaeggi.pokerstats.web.rest.mapper;


import ch.rogerjaeggi.pokerstats.domain.Season;
import ch.rogerjaeggi.pokerstats.web.rest.dto.SeasonDto;

import java.util.List;

public class SeasonMapper {

    public static SeasonDto toDto(Season season) {
        return SeasonMapper.toDto(season, null);
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
