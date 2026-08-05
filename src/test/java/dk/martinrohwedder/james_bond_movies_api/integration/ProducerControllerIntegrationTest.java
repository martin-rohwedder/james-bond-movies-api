package dk.martinrohwedder.james_bond_movies_api.integration;

import dk.martinrohwedder.james_bond_movies_api.entities.Producer;
import dk.martinrohwedder.james_bond_movies_api.repositories.ProducerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProducerControllerIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private ProducerRepository producerRepository;

    @Override
    protected String baseUrl() {
        return "/api/producers";
    }

    // -------------------------------------------------------------------------
    // GET /api/producers/{id}
    // -------------------------------------------------------------------------

    @Test
    void should_return_producer_by_id() throws Exception {
        Producer producer = producerRepository.findAllByOrderByNameAsc().getFirst();

        getById(producer.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(producer.getId().toString()))
                .andExpect(jsonPath("$.name").value(producer.getName()));
    }

    @Test
    void should_return_complete_producer_structure_by_id() throws Exception {
        Producer producer = producerRepository.findAllByOrderByNameAsc().getFirst();

        ResultActions result = getById(producer.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(producer.getId().toString()))
                .andExpect(jsonPath("$.name").value(producer.getName()))
                .andExpect(jsonPath("$.biography").value(producer.getBiography()))
                .andExpect(jsonPath("$.nationality").value(producer.getNationality()))
                .andExpect(jsonPath("$.date_of_birth").value(producer.getDateOfBirth().toString()))
                .andExpect(jsonPath("$.movies").isArray());

        if (producer.getDateOfDeath() != null) {
            result.andExpect(jsonPath("$.date_of_death")
                    .value(producer.getDateOfDeath().toString()));
        } else {
            result.andExpect(jsonPath("$.date_of_death").value(nullValue()));
        }
    }

    @Test
    void should_return_status_not_found_when_producer_id_is_unknown() throws Exception {
        getById(UUID.randomUUID())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_bad_request_for_invalid_uuid() throws Exception {
        getById("not-a-uuid")
                .andExpect(status().isBadRequest());
    }

    // -------------------------------------------------------------------------
    // GET /api/producers
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_producers() throws Exception {
        List<Producer> producers = producerRepository.findAllByOrderByNameAsc();
        Producer producer = producers.getFirst();

        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(producer.getId().toString()))
                .andExpect(jsonPath("$[0].name").value(producer.getName()))
                .andExpect(jsonPath("$.length()").value(producers.size()));
    }

    @Test
    void should_include_movies_for_each_producer() throws Exception {
        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies").isArray())
                .andExpect(jsonPath("$[0].movies.length()").isNotEmpty());
    }

    @Test
    void should_return_producers_ordered_by_name() throws Exception {
        List<Producer> producers = producerRepository.findAllByOrderByNameAsc();

        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(producers.get(0).getName()))
                .andExpect(jsonPath("$[1].name").value(producers.get(1).getName()));
    }

    // -------------------------------------------------------------------------
    // GET /api/producers?name=...
    // -------------------------------------------------------------------------

    @Test
    void should_return_producers_by_name() throws Exception {
        String name = "Albert R. Broccoli";
        List<Producer> expected =
                producerRepository.findAllByNameIgnoreCaseOrderByNameAsc(name);

        getByName(name)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expected.size()))
                .andExpect(jsonPath("$[0].id").value(expected.getFirst().getId().toString()))
                .andExpect(jsonPath("$[0].name").value(name));
    }

    @Test
    void should_find_producer_by_name_case_insensitive() throws Exception {
        getByName("aLbErT r. BrOcCoLi")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Albert R. Broccoli"));
    }

    @Test
    void should_return_empty_list_when_producer_name_is_not_found() throws Exception {
        getByName("Unknown Producer")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void should_return_all_producers_when_name_is_blank() throws Exception {
        getByName("")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(producerRepository.count()));
    }

    @Test
    void should_return_movies_ordered_by_movie_number() throws Exception {
        getByName("Albert R. Broccoli")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies[0].movie_number").value(1))
                .andExpect(jsonPath("$[0].movies[1].movie_number").value(2))
                .andExpect(jsonPath("$[0].movies[2].movie_number").value(3));
    }
}