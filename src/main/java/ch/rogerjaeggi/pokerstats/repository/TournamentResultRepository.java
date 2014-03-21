package ch.rogerjaeggi.pokerstats.repository;


import ch.rogerjaeggi.pokerstats.domain.TournamentResult;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class TournamentResultRepository extends JpaRepository<TournamentResult> {

    public TournamentResultRepository() {
        super(TournamentResult.class);
    }

    public List<TournamentResult> getAllForTournament(String tournamentUuid) {
        Query query = getEntityManager().createQuery("from TournamentResult where tournament.uuid=:tournamentUuid");
        query.setParameter("tournamentUuid", tournamentUuid);
        return Collections.checkedList(query.getResultList(), TournamentResult.class);
    }
}
