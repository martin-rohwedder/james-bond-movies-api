package dk.martinrohwedder.james_bond_movies_api.integration;

import dk.martinrohwedder.james_bond_movies_api.entities.Actor;
import dk.martinrohwedder.james_bond_movies_api.repositories.ActorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.nullValue;

public class ActorControllerIntegrationTest extends AbstractIntegrationTest {
    private static final String BASE_URL = "/api/actors";

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MockMvc mockMvc;

    // -------------------------------------------------------------------------
    // Helper methods
    // -------------------------------------------------------------------------

    private ResultActions getActor(UUID id) throws Exception {
        return mockMvc.perform(get(BASE_URL + "/{id}", id));
    }

    private ResultActions getActor(String id) throws Exception {
        return mockMvc.perform(get(BASE_URL + "/{id}", id));
    }

    private ResultActions getActors() throws Exception {
        return mockMvc.perform(get(BASE_URL));
    }

    private ResultActions getActorsByName(String name, boolean includeMovies) throws Exception {
        var request = get(BASE_URL)
                .param("includeMovies", String.valueOf(includeMovies));

        if (name != null) {
            request.param("name", name);
        }

        return mockMvc.perform(request);
    }

    // -------------------------------------------------------------------------
    // GET /api/actors/{id}
    // -------------------------------------------------------------------------

    @Test
    void should_return_actor_by_id() throws Exception {
        Actor actor = actorRepository.findAllByOrderByNameAsc().getFirst();

        getActor(actor.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(actor.getId().toString()))
                .andExpect(jsonPath("$.name").value(actor.getName()));
    }

    @Test
    void should_return_complete_actor_structure_by_id() throws Exception {
        Actor actor = actorRepository.findAllByOrderByNameAsc().getFirst();

        ResultActions result = getActor(actor.getId())
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
        getActor("168088f0-e705-446b-96f4-cf1fdb035856")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movies[0].movie_number").value(1))
                .andExpect(jsonPath("$.movies[1].movie_number").value(2));
    }

    @Test
    void should_return_status_not_found_when_actor_id_given_is_wrong()  throws Exception {
        getActor("41e7c4a8-ad00-4137-9c83-55edd8c58fe7")
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_bad_request_for_invalid_uuid() throws Exception {
        getActor("not-a-uuid")
                .andExpect(status().isBadRequest());
    }

    // -------------------------------------------------------------------------
    // GET /api/actors
    // -------------------------------------------------------------------------

    @Test
    void should_return_all_actors() throws Exception {
        List<Actor> actors = actorRepository.findAllByOrderByNameAsc();
        Actor actor = actors.getFirst();

        getActors()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(actor.getId().toString()))
                .andExpect(jsonPath("$[0].name").value(actor.getName()))
                .andExpect(jsonPath("$[0].character_role").value(actor.getCharacterRole()))
                .andExpect(jsonPath("$.length()").value(actors.size()));
    }

    @Test
    void should_include_movies_by_default() throws Exception {
        getActors()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies").isArray())
                .andExpect(jsonPath("$[0].movies.length()").isNotEmpty());
    }

    @Test
    void should_return_same_result_when_include_movies_is_true() throws Exception {
        getActorsByName(null, true)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies").isArray())
                .andExpect(jsonPath("$[0].movies.length()").isNotEmpty());
    }

    @Test
    void should_exclude_movies_when_include_movies_is_false() throws Exception {
        getActorsByName(null, false)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movies").isArray())
                .andExpect(jsonPath("$[0].movies.length()").value(0));
    }

    @Test
    void should_return_bad_request_for_invalid_include_movies_parameter() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("includeMovies", "invalid"))
                .andExpect(status().isBadRequest());
    }

    // -------------------------------------------------------------------------
    // GET /api/actors?name=...
    // -------------------------------------------------------------------------

    @Test
    void should_return_actors_by_name() throws Exception {
        String actorName = "Joe Don Baker";
        List<Actor> expected = actorRepository.findAllByNameIgnoreCaseOrderByNameAsc(actorName);

        getActorsByName(actorName, true)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(expected.getFirst().getId().toString()))
                .andExpect(jsonPath("$[0].name").value(expected.getFirst().getName()))
                .andExpect(jsonPath("$.length()").value(expected.size()));
    }

    @Test
    void should_return_actors_by_name_without_movies() throws Exception {
        getActorsByName("Joe Don Baker", false)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Joe Don Baker"))
                .andExpect(jsonPath("$[0].movies.length()").value(0));
    }

    @Test
    void should_return_actors_by_name_with_special_characters() throws Exception {
        String actorName = "Gert Fröbe";
        List<Actor> expected = actorRepository.findAllByNameIgnoreCaseOrderByNameAsc(actorName);

        getActorsByName(actorName, true)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(expected.getFirst().getId().toString()))
                .andExpect(jsonPath("$[0].name").value(expected.getFirst().getName()))
                .andExpect(jsonPath("$.length()").value(expected.size()));
    }

    @Test
    void should_return_actors_by_name_case_insensitive_with_special_character() throws Exception {
        getActorsByName("gert fröbe", true)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Gert Fröbe"));
    }

    @Test
    void should_find_actor_by_name_case_insensitive() throws Exception {
        getActorsByName("sean coNNerY", true)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sean Connery"));
    }

    @Test
    void should_return_empty_list_when_actor_name_given_is_wrong()  throws Exception {
        getActorsByName("Wrong Actor Name", true)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void should_return_all_actors_when_name_is_blank() throws Exception {
        getActorsByName("", true)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(actorRepository.count()));
    }
}
