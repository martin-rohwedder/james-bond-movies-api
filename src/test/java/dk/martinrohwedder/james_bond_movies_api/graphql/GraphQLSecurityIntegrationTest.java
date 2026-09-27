package dk.martinrohwedder.james_bond_movies_api.graphql;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Testcontainers;

@Import(TestcontainersConfiguration.class)
@Testcontainers
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class GraphQLSecurityIntegrationTest {
    @Value("${app.security.api-key}")
    private String apiKey;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldRejectGraphQLRequestWithoutApiKey() {
        webTestClient
                .post()
                .uri("/graphql")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            "query": "{ movies { id title } }"
                        }
                        """)
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    void shouldRejectGraphQLRequestWithInvalidApiKey() {
        webTestClient
                .post()
                .uri("/graphql")
                .header("X-API-Key", "wrong-api-key")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .bodyValue("""
                    {
                        "query": "{ movies { id title } }"
                    }
                    """)
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    void shouldAllowGraphQLRequestWithValidApiKey() {
        webTestClient
                .post()
                .uri("/graphql")
                .header("X-API-Key", apiKey)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .bodyValue("""
                    {
                        "query": "{ movies { id title } }"
                    }
                    """)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.data.movies")
                .isArray();
    }
}
