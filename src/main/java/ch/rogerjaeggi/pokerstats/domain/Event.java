package ch.rogerjaeggi.pokerstats.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "T_EVENT")
public class Event extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_SEASON")
    private Season season;

    @Column(name = "_SEASON", updatable = false, insertable = false)
    private Long seasonId;

    @Column(name = "_NAME")
    private String name;

    @Column(name = "_DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate date;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "venueId")
    //private Venue venue;

    //@Column(name = "venueId", updatable = false, insertable = false)
    //private Long venueId;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "host")
    //private Player host;

    //@Column(name = "host", updatable = false, insertable = false)
    //private Long hostPlayerId;

    //@OneToMany(mappedBy = "event")
    //private List<Tournament> tournaments;

    @Column(name = "_UPDATED", insertable = false, updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime updated;

}