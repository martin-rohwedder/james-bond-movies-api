package dk.martinrohwedder.james_bond_movies_api.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long start = System.currentTimeMillis();

        filterChain.doFilter(request, response);

        String query = request.getQueryString();
        String uri = query == null
                ? request.getRequestURI()
                : request.getRequestURI() + "?" + query;

        log.info("{} {} - {} ({} ms)",
                request.getMethod(),
                uri,
                response.getStatus(),
                System.currentTimeMillis() - start);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/favicon.ico");
    }
}
