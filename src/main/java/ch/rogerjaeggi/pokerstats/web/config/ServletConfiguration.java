package ch.rogerjaeggi.pokerstats.web.config;

import ch.rogerjaeggi.pokerstats.web.security.JsonVulnerabilityProtectionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ch.rogerjaeggi.pokerstats.web.rest"}, useDefaultFilters = false,
        includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class) })
public class ServletConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        OpenEntityManagerInViewInterceptor interceptor = new OpenEntityManagerInViewInterceptor();
        interceptor.setEntityManagerFactory(entityManagerFactory);
        registry.addWebRequestInterceptor(interceptor);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new JsonVulnerabilityProtectionConverter());
    }

}
