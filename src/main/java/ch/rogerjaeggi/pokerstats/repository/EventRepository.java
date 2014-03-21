package ch.rogerjaeggi.pokerstats.repository;

import ch.rogerjaeggi.pokerstats.domain.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class EventRepository extends JpaRepository<Event> {

    public EventRepository() {
        super(Event.class);
    }

    public List<Event> getBySeason(String seasonUuid) {
        Query query = getEntityManager().createQuery("from Event where season.uuid=:seasonUuid");
        query.setParameter("seasonUuid", seasonUuid);
        return Collections.checkedList(query.getResultList(), Event.class);
    }

}