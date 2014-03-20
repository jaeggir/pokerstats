package ch.rogerjaeggi.pokerstats.web.rest.mapper;


import ch.rogerjaeggi.pokerstats.domain.Season;
import ch.rogerjaeggi.pokerstats.web.rest.dto.SeasonDto;

public class SeasonMapper {

    public static SeasonDto toDto(Season season) {
        SeasonDto dto = new SeasonDto();
        dto.setUuid(season.getUuid());
        dto.setName(season.getName());
        dto.setCurrent(season.getCurrent());
        return dto;
    }
}
