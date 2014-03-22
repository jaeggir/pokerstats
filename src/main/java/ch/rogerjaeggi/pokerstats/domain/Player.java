package ch.rogerjaeggi.pokerstats.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "T_PLAYER")
public class Player extends BaseEntity {

    @Column(name = "_NICKNAME")
    private String nickname;

    @Column(name = "_GUEST")
    private Boolean guest;

    @Column(name = "_BIRTHDAY")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate birthday;

    @Column(name = "_JOINED")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate joined;

    @Column(name = "_UPDATED")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime updated;

}
