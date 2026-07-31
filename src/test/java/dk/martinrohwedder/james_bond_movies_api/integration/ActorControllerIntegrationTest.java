package dk.martinrohwedder.james_bond_movies_api.integration;

import dk.martinrohwedder.james_bond_movies_api.entities.Actor;
import dk.martinrohwedder.james_bond_movies_api.repositories.ActorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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

    // Helper methods for reducing redundancy

    private ResultActions getActor(UUID id) throws Exception {
        return mockMvc.perform(get(BASE_URL + "/{id}", id));
    }

    private ResultActions getActor(String id) throws Exception {
        return mockMvc.perform(get(BASE_URL + "/{id}", id));
    }

    @Test
    void should_return_actor_by_id() throws Exception {
        Actor actor = actorRepository.findAll().getFirst();

        getActor(actor.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(actor.getId().toString()))
                .andExpect(jsonPath("$.name").value(actor.getName()));
    }

    @Test
    void should_return_complete_actor_structure_by_id() throws Exception {
        Actor actor = actorRepository.findAll().getFirst();

        ResultActions result = getActor(actor.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(actor.getId().toString()))
                .andExpect(jsonPath("$.name").value(actor.getName()))
                .andExpect(jsonPath("$.character_role").value(actor.getCharacterRole()))
                .andExpect(jsonPath("$.biography").value(actor.getBiography()))
                .andExpect(jsonPath("$.nationality").value(actor.getNationality()))
                .andExpect(jsonPath("$.date_of_birth").value(actor.getDateOfBirth().toString()));

        if (actor.getDateOfDeath() != null) {
            result.andExpect(jsonPath("$.date_of_death").value(actor.getDateOfDeath()));
        }
        else {
            result.andExpect(jsonPath("$.date_of_death").value(nullValue()));
        }
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
}
