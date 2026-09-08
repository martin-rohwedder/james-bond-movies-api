package dk.martinrohwedder.james_bond_movies_api.integration;

import dk.martinrohwedder.james_bond_movies_api.entities.Writer;
import dk.martinrohwedder.james_bond_movies_api.repositories.WriterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.nullValue;

public class WriterControllerIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private WriterRepository writerRepository;

    @Override
    protected String baseUrl() {
        return "/api/writers";
    }

    // -------------------------------------------------------------------------
    // GET /api/writers/{id}
    // -------------------------------------------------------------------------

    @Test
    void should_return_writer_by_id() throws Exception {
        Writer writer = writerRepository.findAllByOrderByNameAsc().getFirst();

        getById(writer.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(writer.getId().toString()))
                .andExpect(jsonPath("$.name").value(writer.getName()));
    }

    @Test
    void should_return_complete_writer_structure_by_id() throws Exception {
        Writer writer = writerRepository.findAllByOrderByNameAsc().getFirst();

        ResultActions result = getById(writer.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(writer.getId().toString()))
                .andExpect(jsonPath("$.name").value(writer.getName()))
                .andExpect(jsonPath("$.date_of_birth").value(writer.getDateOfBirth().toString()))
                .andExpect(jsonPath("$.movies").isArray())
                .andExpect(jsonPath("$.movies").isNotEmpty());

        if (writer.getDateOfDeath() != null) {
            result.andExpect(jsonPath("$.date_of_death").value(writer.getDateOfDeath().toString()));
        }
        else {
            result.andExpect(jsonPath("$.date_of_death").value(nullValue()));
        }
    }

    @Test
    void should_return_movies_with_expected_structure_by_id() throws Exception {
        Writer writer = writerRepository.findAllByOrderByNameAsc().getFirst();

        getById(writer.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movies").isArray())
                .andExpect(jsonPath("$.movies").isNotEmpty())
                .andExpect(jsonPath("$.movies[0].id").exists())
                .andExpect(jsonPath("$.movies[0].title").exists())
                .andExpect(jsonPath("$.movies[0].movie_number").exists());
    }

    @Test
    void should_return_movies_ordered_by_movie_number() throws Exception {
        Writer writer = writerRepository.findAllByNameIgnoreCaseOrderByNameAsc("Ian Fleming").getFirst();

        getById(writer.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movies[0].movie_number").value(1))
                .andExpect(jsonPath("$.movies[1].movie_number").value(2));
    }

    @Test
    void should_return_status_not_found_when_writer_id_given_is_wrong()  throws Exception {
        getById("41e7c4a8-ad00-4137-9c83-55edd8c58fe7")
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_bad_request_for_invalid_writer_uuid() throws Exception {
        getById("not-a-uuid")
                .andExpect(status().isBadRequest());
    }

    // -------------------------------------------------------------------------
    // GET /api/writers
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_writers() throws Exception {
        List<Writer> writers = writerRepository.findAllByOrderByNameAsc();
        Writer writer = writers.getFirst();

        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(writer.getId().toString()))
                .andExpect(jsonPath("$[0].name").value(writer.getName()))
                .andExpect(jsonPath("$.length()").value(writers.size()));
    }

    @Test
    void should_include_movies_by_default() throws Exception {
        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies").isArray())
                .andExpect(jsonPath("$[0].movies").isNotEmpty());
    }

    @Test
    void should_return_same_result_when_include_movies_is_true() throws Exception {
        getWithParams("includeMovies", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies").isArray())
                .andExpect(jsonPath("$[0].movies").isNotEmpty());
    }

    @Test
    void should_exclude_movies_when_include_movies_is_false() throws Exception {
        getWithParams("includeMovies", "false")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies").isArray())
                .andExpect(jsonPath("$[0].movies.length()").value(0));
    }

    @Test
    void should_return_bad_request_for_invalid_include_movies_parameter() throws Exception {
        getWithParams("includeMovies", "invalid")
                .andExpect(status().isBadRequest());
    }

    // -------------------------------------------------------------------------
    // GET /api/writers?name=...
    // -------------------------------------------------------------------------

    @Test
    void should_return_writers_by_name() throws Exception {
        String writerName = "Ian Fleming";
        List<Writer> expected = writerRepository.findAllByNameIgnoreCaseOrderByNameAsc(writerName);

        getWithParams("name", writerName, "includeMovies", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(expected.getFirst().getId().toString()))
                .andExpect(jsonPath("$[0].name").value(expected.getFirst().getName()))
                .andExpect(jsonPath("$.length()").value(expected.size()));
    }

    @Test
    void should_return_writer_by_name_without_movies() throws Exception {
        getWithParams("name", "Ian Fleming", "includeMovies", "false")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ian Fleming"))
                .andExpect(jsonPath("$[0].movies.length()").value(0));
    }

    @Test
    void should_return_writers_by_name_case_insensitive() throws Exception {
        getWithParams("name", "iaN fleMiNG", "includeMovies", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ian Fleming"));
    }

    @Test
    void should_return_empty_list_when_writer_name_given_is_wrong()  throws Exception {
        getWithParams("name", "Wrong Writer Name", "includeMovies", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void should_return_all_writers_when_name_is_blank()  throws Exception {
        getWithParams("name", "", "includeMovies", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(writerRepository.count()));
    }
}
