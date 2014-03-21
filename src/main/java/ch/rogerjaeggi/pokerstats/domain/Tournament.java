package ch.rogerjaeggi.pokerstats.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "T_TOURNAMENT")
public class Tournament extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_EVENT")
    private Event event;

    @Column(name = "_EVENT", updatable = false, insertable = false)
    private String eventUuid;

    @Column(name = "_ROUND")
    private Integer round;

    @Column(name = "_STATUS")
    private Integer status;

    @OneToMany(mappedBy = "tournamentUuid")
    private List<TournamentResult> results;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_RESULT_SUBMITTED_BY")
    private Player resultSubmittedBy;

    @Column(name = "_RESULT_SUBMITTED_BY", updatable = false, insertable = false)
    private String resultSubmittedByPlayerUuid;

    /*@Column(name = "_CURRENT_TIME")
    private Integer currentTime;

    @Column(name = "currentLevel")
    private Integer currentLevel;

    @Column(name = "currentLevelTime")
    private Integer currentLevelTime;*/

    @Column(name = "_UPDATED", insertable = false, updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime updated;
}
