package ch.rogerjaeggi.pokerstats.repository;

import ch.rogerjaeggi.pokerstats.domain.Player;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerRepository extends JpaRepository<Player> {

    public PlayerRepository() {
        super(Player.class);
    }
}
