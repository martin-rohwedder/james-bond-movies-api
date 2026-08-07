package dk.martinrohwedder.james_bond_movies_api.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RequestLoggingFilterIntegrationTest extends AbstractIntegrationTest {

    @Override
    protected String baseUrl() {
        return "/api/directors";
    }

    @Test
    void should_process_api_requests_through_logging_filter() throws Exception {
        getAll()
                .andExpect(status().isOk());
    }

    @Test
    void should_process_requests_with_query_parameters() throws Exception {
        getWithParams("name", "Guy Hamilton")
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "/swagger-ui/index.html",
            "/v3/api-docs"
    })
    void should_not_require_api_key_for_excluded_paths(String path) throws Exception {
        getPathWithoutApiKey(path)
                .andExpect(status().isOk());
    }
}
