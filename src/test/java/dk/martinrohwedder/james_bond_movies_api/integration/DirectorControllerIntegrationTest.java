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

    private ResultActions getDirectorsByName(String name) throws Exception {
        return mockMvc.perform(get(BASE_URL).param("name", name));
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
    void should_return_complete_director_structure_with_movies() throws Exception {
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
    void should_return_directors_by_name() throws Exception {
        String name = "Guy Hamilton";
        List<Director> expected = directorRepository.findAllByNameIgnoreCaseOrderByNameAsc(name);

        getDirectorsByName(name)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expected.size()))
                .andExpect(jsonPath("$[0].id").value(expected.getFirst().getId().toString()))
                .andExpect(jsonPath("$[0].name").value(name));
    }

    @Test
    void should_find_director_by_name_case_insensitive() throws Exception {
        getDirectorsByName("gUy hAMilTon")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Guy Hamilton"));
    }

    @Test
    void should_return_empty_list_when_director_name_is_not_found() throws Exception {
        getDirectorsByName("Unknown Director")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void should_return_all_directors_when_name_is_blank() throws Exception {
        getDirectorsByName("")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(directorRepository.count()));
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

    @Test
    void should_return_movies_ordered_by_movie_number() throws Exception {
        getDirectorsByName("Terence Young")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies[0].movie_number").value(1))
                .andExpect(jsonPath("$[0].movies[1].movie_number").value(2))
                .andExpect(jsonPath("$[0].movies[2].movie_number").value(4));
    }
}