package dk.martinrohwedder.james_bond_movies_api.integration;

import dk.martinrohwedder.james_bond_movies_api.entities.Director;
import dk.martinrohwedder.james_bond_movies_api.repositories.DirectorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DirectorControllerIntegrationTest extends AbstractIntegrationTest {

    private static final String BASE_URL = "/api/directors";

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private MockMvc mockMvc;

    // -------------------------------------------------------------------------
    // Helper method
    // -------------------------------------------------------------------------

    private ResultActions getDirectors() throws Exception {
        return mockMvc.perform(get(BASE_URL));
    }

    // -------------------------------------------------------------------------
    // GET /api/directors
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_directors() throws Exception {
        List<Director> directors = directorRepository.findAllByOrderByNameAsc();
        Director director = directors.getFirst();

        getDirectors()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(director.getId().toString()))
                .andExpect(jsonPath("$[0].name").value(director.getName()))
                .andExpect(jsonPath("$.length()").value(directors.size()));
    }

    @Test
    void should_return_complete_director_structure() throws Exception {
        Director director = directorRepository.findAllByOrderByNameAsc().getFirst();

        ResultActions result = getDirectors()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(director.getId().toString()))
                .andExpect(jsonPath("$[0].name").value(director.getName()))
                .andExpect(jsonPath("$[0].biography").value(director.getBiography()))
                .andExpect(jsonPath("$[0].nationality").value(director.getNationality()))
                .andExpect(jsonPath("$[0].date_of_birth").value(director.getDateOfBirth().toString()))
                .andExpect(jsonPath("$[0].movies").isArray());

        if (director.getDateOfDeath() != null) {
            result.andExpect(jsonPath("$[0].date_of_death")
                    .value(director.getDateOfDeath().toString()));
        } else {
            result.andExpect(jsonPath("$[0].date_of_death").value(nullValue()));
        }
    }

    @Test
    void should_include_movies_for_each_director() throws Exception {
        getDirectors()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies").isArray())
                .andExpect(jsonPath("$[0].movies.length()").isNotEmpty());
    }

    @Test
    void should_return_directors_ordered_by_name() throws Exception {
        List<Director> directors = directorRepository.findAllByOrderByNameAsc();

        getDirectors()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(directors.get(0).getName()))
                .andExpect(jsonPath("$[1].name").value(directors.get(1).getName()));
    }
}