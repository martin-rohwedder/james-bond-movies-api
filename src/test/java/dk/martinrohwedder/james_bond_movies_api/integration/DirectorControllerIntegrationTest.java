package dk.martinrohwedder.james_bond_movies_api.integration;

import dk.martinrohwedder.james_bond_movies_api.entities.Director;
import dk.martinrohwedder.james_bond_movies_api.repositories.DirectorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DirectorControllerIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private DirectorRepository directorRepository;

    @Override
    protected String baseUrl() {
        return "/api/directors";
    }

    // -------------------------------------------------------------------------
    // GET /api/directors/{id}
    // -------------------------------------------------------------------------

    @Test
    void should_return_director_by_id() throws Exception {
        Director director = directorRepository.findAllByOrderByNameAsc().getFirst();

        getById(director.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(director.getId().toString()))
                .andExpect(jsonPath("$.name").value(director.getName()));
    }

    @Test
    void should_return_complete_director_structure_by_id() throws Exception {
        Director director = directorRepository.findAllByOrderByNameAsc().getFirst();

        ResultActions result = getById(director.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(director.getId().toString()))
                .andExpect(jsonPath("$.name").value(director.getName()))
                .andExpect(jsonPath("$.biography").value(director.getBiography()))
                .andExpect(jsonPath("$.nationality").value(director.getNationality()))
                .andExpect(jsonPath("$.date_of_birth").value(director.getDateOfBirth().toString()))
                .andExpect(jsonPath("$.movies").isArray());

        if (director.getDateOfDeath() != null) {
            result.andExpect(jsonPath("$.date_of_death")
                    .value(director.getDateOfDeath().toString()));
        } else {
            result.andExpect(jsonPath("$.date_of_death").value(nullValue()));
        }
    }

    @Test
    void should_return_status_not_found_when_director_id_is_unknown() throws Exception {
        getById(UUID.randomUUID())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_bad_request_for_invalid_uuid() throws Exception {
        getById("not-a-uuid")
                .andExpect(status().isBadRequest());
    }

    // -------------------------------------------------------------------------
    // GET /api/directors
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_directors() throws Exception {
        List<Director> directors = directorRepository.findAllByOrderByNameAsc();
        Director director = directors.getFirst();

        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(director.getId().toString()))
                .andExpect(jsonPath("$[0].name").value(director.getName()))
                .andExpect(jsonPath("$.length()").value(directors.size()));
    }

    @Test
    void should_include_movies_for_each_director() throws Exception {
        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies").isArray())
                .andExpect(jsonPath("$[0].movies.length()").isNotEmpty());
    }

    @Test
    void should_return_directors_ordered_by_name() throws Exception {
        List<Director> directors = directorRepository.findAllByOrderByNameAsc();

        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(directors.get(0).getName()))
                .andExpect(jsonPath("$[1].name").value(directors.get(1).getName()));
    }

    // -------------------------------------------------------------------------
    // GET /api/directors?name=...
    // -------------------------------------------------------------------------

    @Test
    void should_return_directors_filtered_by_name() throws Exception {
        String name = "Guy Hamilton";
        List<Director> expected = directorRepository.findAllByNameIgnoreCaseOrderByNameAsc(name);

        getByName(name)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expected.size()))
                .andExpect(jsonPath("$[0].id").value(expected.getFirst().getId().toString()))
                .andExpect(jsonPath("$[0].name").value(name));
    }

    @Test
    void should_find_director_by_name_case_insensitive() throws Exception {
        getByName("gUy hAMilTon")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Guy Hamilton"));
    }

    @Test
    void should_return_empty_list_when_director_name_is_not_found() throws Exception {
        getByName("Unknown Director")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void should_return_all_directors_when_name_is_blank() throws Exception {
        getByName("")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(directorRepository.count()));
    }

    @Test
    void should_return_movies_ordered_by_movie_number() throws Exception {
        getByName("Terence Young")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies[0].movie_number").value(1))
                .andExpect(jsonPath("$[0].movies[1].movie_number").value(2))
                .andExpect(jsonPath("$[0].movies[2].movie_number").value(4));
    }
}