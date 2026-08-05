package dk.martinrohwedder.james_bond_movies_api.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
            "/v3/api-docs",
            "/favicon.ico"
    })
    void should_skip_excluded_paths(String path) throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().isNotFound());
    }
}
