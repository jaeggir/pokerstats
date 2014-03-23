package ch.rogerjaeggi.pokerstats.domain;


import lombok.Getter;

@Getter
public enum TournamentStatus {

    RUNNING(0),
    FINISHED(1);

    private int id;

    private TournamentStatus(int id) {
        this.id = id;
    }
}
