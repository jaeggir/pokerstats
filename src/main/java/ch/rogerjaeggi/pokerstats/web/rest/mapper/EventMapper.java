package ch.rogerjaeggi.pokerstats.web.rest.mapper;


import ch.rogerjaeggi.pokerstats.domain.Event;
import ch.rogerjaeggi.pokerstats.web.rest.dto.EventDto;

public class EventMapper {

    public static EventDto toDto(Event event) {
        EventDto dto = new EventDto();
        dto.setUuid(event.getUuid());
        dto.setName(event.getName());
        dto.setDate(event.getDate().toDateTimeAtStartOfDay().getMillis());
        dto.setUpdated(event.getUpdated().toDateTime().getMillis());
        return dto;
    }
}
