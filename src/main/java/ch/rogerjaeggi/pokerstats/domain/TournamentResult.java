package ch.rogerjaeggi.pokerstats.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "T_TOURNAMENT_RESULT")
public class TournamentResult extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_TOURNAMENT")
    private Tournament tournament;

    @Column(name = "_TOURNAMENT", updatable = false, insertable = false)
    private String tournamentUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_PLAYER")
    private Player player;

    @Column(name = "_PLAYER", updatable = false, insertable = false)
    private String playerUuid;

    @Column(name = "_RANK")
    private Integer rank;

    @Column(name = "_ANTE")
    private Integer ante;

    @Column(name = "_WIN")
    private Integer win;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_ELIMINATED_BY")
    private Player eliminatedBy;

    @Column(name = "_ELIMINATED_BY", updatable = false, insertable = false)
    private String eliminatedByPlayerUuid;

    @Column(name = "_UPDATED", insertable = false, updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @Generated(GenerationTime.ALWAYS)
    private LocalDateTime updated;
}
