package ch.rogerjaeggi.pokerstats.web.rest.mapper;


import ch.rogerjaeggi.pokerstats.domain.Event;
import ch.rogerjaeggi.pokerstats.web.rest.dto.EventDto;

import java.util.List;

public class EventMapper {

    public static EventDto toDto(Event event, List<String> tournaments) {
        EventDto dto = new EventDto();
        dto.setUuid(event.getUuid());
        dto.setName(event.getName());
        dto.setDate(event.getDate().toDateTimeAtStartOfDay().getMillis());
        dto.setUpdated(event.getUpdated().toDateTime().getMillis());
        dto.setHostPlayerUuid(event.getHostPlayerUuid());
        dto.setTournaments(tournaments);
        return dto;
    }
}
