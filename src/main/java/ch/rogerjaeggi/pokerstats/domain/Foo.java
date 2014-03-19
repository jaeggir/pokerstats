package ch.rogerjaeggi.pokerstats.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Entity
@Table(name = "T_FOO")
public class Foo extends BaseEntity {

    @Column(name = "_BAR")
    @Size(min = 0, max = 100)
    private String bar;

}
