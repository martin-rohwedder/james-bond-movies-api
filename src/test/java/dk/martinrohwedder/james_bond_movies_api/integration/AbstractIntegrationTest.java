package dk.martinrohwedder.james_bond_movies_api.integration;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {
    @Autowired
    protected MockMvc mockMvc;

    @Value("${app.security.api-key:test-api-key}")
    private String apiKey;

    protected abstract String baseUrl();

    // -------------------------------------------------------------------------
    // Authenticated helper methods (default)
    // -------------------------------------------------------------------------

    protected ResultActions getWithParams(String... params) throws Exception {
        var request = get(baseUrl()).header("X-API-Key", this.apiKey);

        for (int i = 0; i < params.length; i += 2) {
            request.param(params[i], params[i + 1]);
        }

        return mockMvc.perform(request);
    }

    protected ResultActions getAll() throws Exception {
        return mockMvc.perform(get(baseUrl())
                .header("X-API-Key", this.apiKey));
    }

    protected ResultActions getById(UUID id) throws Exception {
        return mockMvc.perform(get(baseUrl() + "/{id}", id)
                .header("X-API-Key", this.apiKey));
    }

    protected ResultActions getById(String id) throws Exception {
        return mockMvc.perform(get(baseUrl() + "/{id}", id)
                .header("X-API-Key", this.apiKey));
    }

    protected ResultActions getByName(String name) throws Exception {
        return mockMvc.perform(get(baseUrl()).param("name", name)
                .header("X-API-Key", this.apiKey));
    }

    // -------------------------------------------------------------------------
    // Request Loggin Filter helpers
    // -------------------------------------------------------------------------

    protected ResultActions getPathWithoutApiKey(String path) throws Exception {
        return mockMvc.perform(get(path));
    }

    // -------------------------------------------------------------------------
    // Security testing helpers
    // -------------------------------------------------------------------------

    protected ResultActions getAllWithApiKey(String apiKey) throws Exception {
        return mockMvc.perform(get(baseUrl())
                .header("X-API-Key", apiKey));
    }

    protected ResultActions getAllWithoutApiKey() throws Exception {
        return mockMvc.perform(get(baseUrl()));
    }
}
