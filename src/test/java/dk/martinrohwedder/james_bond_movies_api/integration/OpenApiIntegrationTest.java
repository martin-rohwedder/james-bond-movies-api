package dk.martinrohwedder.james_bond_movies_api.integration;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OpenApiIntegrationTest extends AbstractIntegrationTest {

    @Override
    protected String baseUrl() {
        return "/v3/api-docs";
    }

    @Test
    void should_expose_openapi_documentation() throws Exception {
        getPathWithoutApiKey(baseUrl())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info.title").value("James Bond Movies API"))
                .andExpect(jsonPath("$.components.securitySchemes.ApiKeyAuth.type").value("apiKey"))
                .andExpect(jsonPath("$.components.securitySchemes.ApiKeyAuth.name").value("X-API-Key"))
                .andExpect(jsonPath("$.security[0].ApiKeyAuth").exists());
    }
}