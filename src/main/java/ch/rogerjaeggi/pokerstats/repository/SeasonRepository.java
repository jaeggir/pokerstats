package ch.rogerjaeggi.pokerstats.repository;

import ch.rogerjaeggi.pokerstats.domain.Season;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class SeasonRepository extends JpaRepository<Season> {

    public SeasonRepository() {
        super(Season.class);
    }

    public Season getCurrentSeason() {
        Query query = getEntityManager().createQuery("from Season where current=1");
        return (Season) query.getSingleResult();
    }
}
