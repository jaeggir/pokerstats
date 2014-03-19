package ch.rogerjaeggi.pokerstats.repository;

import ch.rogerjaeggi.pokerstats.domain.Foo;
import org.springframework.stereotype.Repository;

@Repository
public class FooRepository extends JpaRepository<Foo> {

    public FooRepository() {
        super(Foo.class);
    }

}
