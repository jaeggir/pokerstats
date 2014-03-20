package ch.rogerjaeggi.pokerstats.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "T_SEASON")
public class Season extends BaseEntity {

    @Column(name = "_NAME", unique = true, nullable = false, updatable = false)
    private String name;

    @Column(name = "_CURRENT", unique = false, updatable = true)
    private Boolean current;

}
