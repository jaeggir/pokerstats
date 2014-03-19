package ch.rogerjaeggi.pokerstats.web.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
        throws IOException, ServletException {

        // HACK remove when upgraded to spring 4.0.3
        if (!req.getRequestURI().startsWith("/api/socket")) {

            if ("OPTIONS".equals(req.getMethod())) {
                res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
                res.addHeader("Access-Control-Allow-Headers", "Authorization, Origin, Content-Type, Accept");
                res.addHeader("Access-Control-Max-Age", "1800");
            }

            String origin = req.getHeader("Origin");
            if (origin != null) {
                res.setHeader("Access-Control-Allow-Origin", origin);
            }

            res.addHeader("Access-Control-Allow-Credentials", "true");
        }

        chain.doFilter(req, res);
	}

}