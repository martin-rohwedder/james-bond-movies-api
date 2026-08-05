package dk.martinrohwedder.james_bond_movies_api.integration;

import dk.martinrohwedder.james_bond_movies_api.entities.Actor;
import dk.martinrohwedder.james_bond_movies_api.repositories.ActorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.nullValue;

public class ActorControllerIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private ActorRepository actorRepository;

    @Override
    protected String baseUrl() {
        return "/api/actors";
    }

    // -------------------------------------------------------------------------
    // GET /api/actors/{id}
    // -------------------------------------------------------------------------

    @Test
    void should_return_actor_by_id() throws Exception {
        Actor actor = actorRepository.findAllByOrderByNameAsc().getFirst();

        getById(actor.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(actor.getId().toString()))
                .andExpect(jsonPath("$.name").value(actor.getName()));
    }

    @Test
    void should_return_complete_actor_structure_by_id() throws Exception {
        Actor actor = actorRepository.findAllByOrderByNameAsc().getFirst();

        ResultActions result = getById(actor.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(actor.getId().toString()))
                .andExpect(jsonPath("$.name").value(actor.getName()))
                .andExpect(jsonPath("$.character_role").value(actor.getCharacterRole()))
                .andExpect(jsonPath("$.biography").value(actor.getBiography()))
                .andExpect(jsonPath("$.nationality").value(actor.getNationality()))
                .andExpect(jsonPath("$.date_of_birth").value(actor.getDateOfBirth().toString()))
                .andExpect(jsonPath("$.movies").isArray())
                .andExpect(jsonPath("$.movies.length()").isNotEmpty());

        if (actor.getDateOfDeath() != null) {
            result.andExpect(jsonPath("$.date_of_death").value(actor.getDateOfDeath().toString()));
        } else {
            result.andExpect(jsonPath("$.date_of_death").value(nullValue()));
        }
    }

    @Test
    void should_return_movies_ordered_by_movie_number() throws Exception {
        getById("168088f0-e705-446b-96f4-cf1fdb035856")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movies[0].movie_number").value(1))
                .andExpect(jsonPath("$.movies[1].movie_number").value(2));
    }

    @Test
    void should_return_status_not_found_when_actor_id_given_is_wrong()  throws Exception {
        getById("41e7c4a8-ad00-4137-9c83-55edd8c58fe7")
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_bad_request_for_invalid_uuid() throws Exception {
        getById("not-a-uuid")
                .andExpect(status().isBadRequest());
    }

    // -------------------------------------------------------------------------
    // GET /api/actors
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_actors() throws Exception {
        List<Actor> actors = actorRepository.findAllByOrderByNameAsc();
        Actor actor = actors.getFirst();

        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(actor.getId().toString()))
                .andExpect(jsonPath("$[0].name").value(actor.getName()))
                .andExpect(jsonPath("$[0].character_role").value(actor.getCharacterRole()))
                .andExpect(jsonPath("$.length()").value(actors.size()));
    }

    @Test
    void should_include_movies_by_default() throws Exception {
        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies").isArray())
                .andExpect(jsonPath("$[0].movies.length()").isNotEmpty());
    }

    @Test
    void should_return_same_result_when_include_movies_is_true() throws Exception {
        getWithParams("includeMovies", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies").isArray())
                .andExpect(jsonPath("$[0].movies.length()").isNotEmpty());
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
    // GET /api/actors?name=...
    // -------------------------------------------------------------------------

    @Test
    void should_return_actors_by_name() throws Exception {
        String actorName = "Joe Don Baker";
        List<Actor> expected = actorRepository.findAllByNameIgnoreCaseOrderByNameAsc(actorName);

        getWithParams("name", actorName, "includeMovies", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(expected.getFirst().getId().toString()))
                .andExpect(jsonPath("$[0].name").value(expected.getFirst().getName()))
                .andExpect(jsonPath("$.length()").value(expected.size()));
    }

    @Test
    void should_return_actors_by_name_without_movies() throws Exception {
        getWithParams("name", "Joe Don Baker", "includeMovies", "false")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Joe Don Baker"))
                .andExpect(jsonPath("$[0].movies.length()").value(0));
    }

    @Test
    void should_return_actors_by_name_with_special_characters() throws Exception {
        String actorName = "Gert Fröbe";
        List<Actor> expected = actorRepository.findAllByNameIgnoreCaseOrderByNameAsc(actorName);

        getWithParams("name", actorName, "includeMovies", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(expected.getFirst().getId().toString()))
                .andExpect(jsonPath("$[0].name").value(expected.getFirst().getName()))
                .andExpect(jsonPath("$.length()").value(expected.size()));
    }

    @Test
    void should_return_actors_by_name_case_insensitive_with_special_character() throws Exception {
        getWithParams("name", "gert fröbe", "includeMovies", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Gert Fröbe"));
    }

    @Test
    void should_find_actor_by_name_case_insensitive() throws Exception {
        getWithParams("name", "sean coNNerY", "includeMovies", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sean Connery"));
    }

    @Test
    void should_return_empty_list_when_actor_name_given_is_wrong()  throws Exception {
        getWithParams("name", "Wrong Actor Name", "includeMovies", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void should_return_all_actors_when_name_is_blank() throws Exception {
        getWithParams("name", "", "includeMovies", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(actorRepository.count()));
    }
}
