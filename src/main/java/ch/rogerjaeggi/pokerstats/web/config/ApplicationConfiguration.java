package ch.rogerjaeggi.pokerstats.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan({
        "ch.rogerjaeggi.pokerstats.domain",
        "ch.rogerjaeggi.pokerstats.repository",
        "ch.rogerjaeggi.pokerstats.service"})
@PropertySource({"classpath:db.properties"})
public class ApplicationConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
