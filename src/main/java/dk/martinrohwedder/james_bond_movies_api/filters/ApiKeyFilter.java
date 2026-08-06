package dk.martinrohwedder.james_bond_movies_api.filters;

import dk.martinrohwedder.james_bond_movies_api.utilities.SecurityPaths;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Value("${app.security.api-key}")
    private String configuredApiKey;

    private static final String API_KEY_HEADER = "X-API-Key";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String providedKey = request.getHeader(API_KEY_HEADER);

        if (configuredApiKey.equals(providedKey)) {

            var authentication = new UsernamePasswordAuthenticationToken(
                    "api-client",
                    null,
                    AuthorityUtils.createAuthorityList("ROLE_API")
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
            return;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid API key");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        for (String pattern : SecurityPaths.PUBLIC_PATHS) {
            if (PATH_MATCHER.match(pattern, path)) {
                return true;
            }
        }

        return false;
    }
}