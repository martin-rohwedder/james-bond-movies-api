package dk.martinrohwedder.james_bond_movies_api.integration;

import dk.martinrohwedder.james_bond_movies_api.TestcontainersConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public abstract class AbstractIntegrationTest {
    @Autowired
    protected MockMvc mockMvc;

    protected abstract String baseUrl();

    protected ResultActions getWithParams(String... params) throws Exception {
        var request = get(baseUrl());

        for (int i = 0; i < params.length; i += 2) {
            request.param(params[i], params[i + 1]);
        }

        return mockMvc.perform(request);
    }

    protected ResultActions getAll() throws Exception {
        return mockMvc.perform(get(baseUrl()));
    }

    protected ResultActions getById(UUID id) throws Exception {
        return mockMvc.perform(get(baseUrl() + "/{id}", id));
    }

    protected ResultActions getById(String id) throws Exception {
        return mockMvc.perform(get(baseUrl() + "/{id}", id));
    }

    protected ResultActions getByName(String name) throws Exception {
        return mockMvc.perform(get(baseUrl()).param("name", name));
    }
}
