package ch.rogerjaeggi.pokerstats.service;

import ch.rogerjaeggi.pokerstats.domain.Foo;
import ch.rogerjaeggi.pokerstats.repository.FooRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Service class for managing Foos.
 */
@Service
@Transactional
public class FooService {

    private final Logger log = LoggerFactory.getLogger(FooService.class);

    @Inject
    private FooRepository fooRepository;

    public void addFoo(String bar) {
        Foo foo = new Foo();
        foo.setBar("bar");
        fooRepository.update(foo);
        log.debug("Changed Information for Foo: {}", foo);
    }
}
