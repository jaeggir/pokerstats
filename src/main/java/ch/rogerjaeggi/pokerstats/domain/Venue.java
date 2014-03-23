package ch.rogerjaeggi.pokerstats.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "T_VENUE")
public class Venue extends BaseEntity {

    @Column(name = "_CURRENT")
    private Boolean current;

    @Column(name = "_NAME")
    private String name;

    @Column(name = "_STREET")
    private String street;

    @Column(name = "_ZIP")
    private Integer zip;

    @Column(name = "_CITY")
    private String city;

    @Column(name = "_COUNTRY")
    private String country;

    @Column(name = "_LATITUDE")
    private BigDecimal latitude;

    @Column(name = "_LONGITUDE")
    private BigDecimal longitude;

    @Column(name = "_UPDATED", updatable = false, insertable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @Generated(GenerationTime.ALWAYS)
    private LocalDateTime updated;
}
