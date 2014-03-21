package ch.rogerjaeggi.pokerstats.repository;


import ch.rogerjaeggi.pokerstats.domain.Venue;
import org.springframework.stereotype.Repository;

@Repository
public class VenueRepository extends JpaRepository<Venue> {

    public VenueRepository() {
        super(Venue.class);
    }
}
