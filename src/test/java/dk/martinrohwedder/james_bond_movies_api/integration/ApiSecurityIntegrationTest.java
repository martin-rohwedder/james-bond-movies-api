package dk.martinrohwedder.james_bond_movies_api.integration;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApiSecurityIntegrationTest extends AbstractIntegrationTest {
    @Override
    protected String baseUrl() {
        return "/api/movies";
    }

    @Test
    void should_return_401_when_api_key_is_missing() throws Exception {
        getAllWithoutApiKey()
                .andExpect(status().isUnauthorized());
    }

    @Test
    void should_return_401_when_api_key_is_invalid() throws Exception {
        getAllWithApiKey("wrong-api-key")
                .andExpect(status().isUnauthorized());
    }

    @Test
    void should_return_200_when_api_key_is_valid() throws Exception {
        getAll()
                .andExpect(status().isOk());
    }

//    @Test
//    void should_not_require_api_key_for_swagger() throws Exception {
//        mockMvc.perform(get("/swagger-ui/index.html"))
//                .andExpect(status().isOk()); // Uncomment this test, when Swagger is enabled
//    }
}
