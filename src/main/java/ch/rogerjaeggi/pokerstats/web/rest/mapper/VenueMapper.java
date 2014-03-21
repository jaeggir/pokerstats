package ch.rogerjaeggi.pokerstats.web.rest.mapper;


import ch.rogerjaeggi.pokerstats.domain.Venue;
import ch.rogerjaeggi.pokerstats.web.rest.dto.VenueDto;

import java.util.LinkedList;
import java.util.List;

public class VenueMapper {

    public static List<VenueDto> toDto(List<Venue> venues) {
        List<VenueDto> dtos = new LinkedList<>();
        for (Venue venue : venues) {
            dtos.add(toDto(venue));
        }
        return dtos;
    }

    public static VenueDto toDto(Venue venue) {
        VenueDto dto = new VenueDto();
        dto.setUuid(venue.getUuid());
        dto.setUpdated(venue.getUpdated().toDateTime().getMillis());
        dto.setCurrent(venue.getCurrent());
        dto.setLatitude(venue.getLatitude());
        dto.setLongitude(venue.getLongitude());
        dto.setName(venue.getName());
        dto.setStreet(venue.getStreet());
        dto.setZip(venue.getZip());
        dto.setCity(venue.getCity());
        dto.setCountry(venue.getCountry());
        return dto;
    }
}
