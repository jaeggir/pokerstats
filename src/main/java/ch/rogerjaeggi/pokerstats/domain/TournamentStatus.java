package ch.rogerjaeggi.pokerstats.domain;


import lombok.Getter;

@Getter
public enum TournamentStatus {

    SCHEDULED(0),
    RUNNING(1),
    FINISHED(2);

    private int id;

    private TournamentStatus(int id) {
        this.id = id;
    }
}
