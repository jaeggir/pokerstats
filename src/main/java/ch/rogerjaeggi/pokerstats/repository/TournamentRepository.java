package ch.rogerjaeggi.pokerstats.repository;

import ch.rogerjaeggi.pokerstats.domain.Tournament;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class TournamentRepository extends JpaRepository<Tournament> {

    public TournamentRepository() {
        super(Tournament.class);
    }

    public List<Tournament> getByEventUuid(String eventUuid) {
        Query query = getEntityManager().createQuery("from Tournament where event.uuid=:eventUuid");
        query.setParameter("eventUuid", eventUuid);
        return Collections.checkedList(query.getResultList(), Tournament.class);
    }
}
