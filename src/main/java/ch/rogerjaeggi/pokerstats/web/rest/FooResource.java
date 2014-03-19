package ch.rogerjaeggi.pokerstats.web.rest;

import ch.rogerjaeggi.pokerstats.domain.Foo;
import ch.rogerjaeggi.pokerstats.repository.FooRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

/**
 * REST controller for managing foos.
 */
@RestController
@RequestMapping("/pokerstats")
public class FooResource {

    private final Logger log = LoggerFactory.getLogger(FooResource.class);

    @Inject
    private FooRepository fooRepository;

    @RequestMapping(value = "/rest/foo/{uuid}", method = RequestMethod.GET, produces = "application/json")
    public Foo getFoo(@PathVariable String uuid, HttpServletResponse response) {
        log.debug("REST request to get Foo : {}", uuid);
        Foo foo = fooRepository.getByUuid(uuid);
        if (foo == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return foo;
    }
}
