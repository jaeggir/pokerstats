package ch.rogerjaeggi.pokerstats.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "T_EVENT")
public class Event extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_SEASON")
    private Season season;

    @Column(name = "_SEASON", updatable = false, insertable = false)
    private String seasonUuid;

    @Column(name = "_NAME")
    private String name;

    @Column(name = "_DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_VENUE")
    private Venue venue;

    @Column(name = "_VENUE", updatable = false, insertable = false)
    private String venueUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_HOST")
    private Player host;

    @Column(name = "_HOST", updatable = false, insertable = false)
    private String hostPlayerUuid;

    @OneToMany(mappedBy = "eventUuid")
    private List<Tournament> tournaments;

    @Column(name = "_UPDATED", updatable = false, insertable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @Generated(GenerationTime.ALWAYS)
    private LocalDateTime updated;

}
