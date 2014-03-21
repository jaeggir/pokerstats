package ch.rogerjaeggi.pokerstats.web.rest.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VenueDto {

    private String uuid;

    private boolean current;

    private String name;

    private String street;

    private Integer zip;

    private String city;

    private String country;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private long updated;
}
