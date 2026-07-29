package dk.martinrohwedder.james_bond_movies_api.integration;

import dk.martinrohwedder.james_bond_movies_api.entities.Movie;
import dk.martinrohwedder.james_bond_movies_api.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MovieControllerIntegrationTest extends AbstractIntegrationTest {
    private static final String BASE_URL = "/api/movies";

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MockMvc mockMvc;

    // Helper methods for reducing redundancy

    private ResultActions getMovies() throws Exception {
        return mockMvc.perform(get(BASE_URL));
    }

    private ResultActions getMovies(boolean excludeActors, boolean excludeProducers) throws Exception {
        return mockMvc.perform(get(BASE_URL)
                .param("excludeActors", String.valueOf(excludeActors))
                .param("excludeProducers", String.valueOf(excludeProducers)));
    }

    private ResultActions getMovie(UUID id) throws Exception {
        return mockMvc.perform(get(BASE_URL + "/{id}", id));
    }

    private ResultActions getMovie(String id) throws Exception {
        return mockMvc.perform(get(BASE_URL + "/{id}", id));
    }

    private int expectedMovieCount() {
        return (int) movieRepository.count();
    }

    @Test
    void should_return_all_movies_ordered_by_movie_number() throws Exception {
        getMovies()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movie_number").value(1))
                .andExpect(jsonPath("$[0].title").value("Dr. No"))
                .andExpect(jsonPath("$[1].movie_number").value(2))
                .andExpect(jsonPath("$[1].title").value("From Russia With Love"))
                .andExpect(jsonPath("$.length()").value(expectedMovieCount()));
    }

    @Test
    void should_return_actors_and_producers_by_default() throws Exception {
        getMovies()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].actors").isArray())
                .andExpect(jsonPath("$[0].producers").isArray());
    }

    @Test
    void should_return_same_result_as_default_when_query_parameters_are_false() throws Exception {
        getMovies(false, false)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].actors").isArray())
                .andExpect(jsonPath("$[0].producers").isArray());
    }

    @Test
    void should_exclude_actors_from_movies_when_query_parameter_is_true() throws Exception {
        getMovies(true, false)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].actors").isEmpty())
                .andExpect(jsonPath("$[0].producers").isArray());
    }

    @Test
    void should_exclude_producers_from_movies_when_query_parameter_is_true() throws Exception {
        getMovies(false, true)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].producers").isEmpty())
                .andExpect(jsonPath("$[0].actors").isArray());
    }

    @Test
    void should_exclude_producers_and_actors_from_movies_when_query_parameters_is_true() throws Exception {
        getMovies(true, true)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].producers").isEmpty())
                .andExpect(jsonPath("$[0].actors").isEmpty());
    }

    @Test
    void should_not_change_number_of_movies_when_excluding_actors() throws Exception {
        getMovies(true, false)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expectedMovieCount()));
    }

    @Test
    void should_not_change_number_of_movies_when_excluding_producers() throws Exception {
        getMovies(false, true)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expectedMovieCount()));
    }

    @Test
    void should_not_change_number_of_movies_when_excluding_actors_and_producers() throws Exception {
        getMovies(true, true)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expectedMovieCount()));
    }

    @Test
    void should_keep_director_and_music_when_excluding_actors_and_producers() throws Exception {
        getMovies(true, true)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].director.name").exists())
                .andExpect(jsonPath("$[0].music.title").exists())
                .andExpect(jsonPath("$[0].actors").isEmpty())
                .andExpect(jsonPath("$[0].producers").isEmpty());
    }

    @Test
    void should_return_bad_request_for_invalid_boolean_parameter() throws Exception {
        mockMvc.perform(get(BASE_URL).param("excludeActors", "invalid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_movie_by_id() throws Exception {
        Movie movie = movieRepository.findAllByOrderByMovieNumberAsc().getFirst();

        getMovie(movie.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(movie.getTitle()))
                .andExpect(jsonPath("$.id").value(movie.getId().toString()));
    }

    @Test
    void should_return_complete_movie_structure_by_id() throws Exception {
        Movie movie = movieRepository.findAllByOrderByMovieNumberAsc().getFirst();

        getMovie(movie.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(movie.getId().toString()))
                .andExpect(jsonPath("$.movie_number").value(movie.getMovieNumber()))
                .andExpect(jsonPath("$.title").value(movie.getTitle()))
                .andExpect(jsonPath("$.short_description").value(movie.getShortDescription()))
                .andExpect(jsonPath("$.long_description").value(movie.getLongDescription()))
                .andExpect(jsonPath("$.trailer_url").value(movie.getTrailerUrl()))
                .andExpect(jsonPath("$.world_premiere").value(movie.getWorldPremiere()))
                .andExpect(jsonPath("$.release_dates").isArray())
                .andExpect(jsonPath("$.release_dates.length()").value(movie.getReleaseDates().size()))
                .andExpect(jsonPath("$.release_dates[0].date_of_release").exists())
                .andExpect(jsonPath("$.release_dates[0].country").exists())
                .andExpect(jsonPath("$.release_dates[0].country_code").exists())
                .andExpect(jsonPath("$.music.title").value(movie.getMusic().getTitle()))
                .andExpect(jsonPath("$.music.performer").value(movie.getMusic().getPerformer()))
                .andExpect(jsonPath("$.music.song_url").value(movie.getMusic().getSongUrl()))
                .andExpect(jsonPath("$.james_bond_actor").value(movie.getJamesBondActor()))
                .andExpect(jsonPath("$.director.name").value(movie.getDirector().getName()))
                .andExpect(jsonPath("$.director.biography").value(movie.getDirector().getBiography()))
                .andExpect(jsonPath("$.director.nationality").value(movie.getDirector().getNationality()))
                .andExpect(jsonPath("$.director.date_of_birth").value(movie.getDirector().getDateOfBirth().toString()))
                .andExpect(jsonPath("$.director.date_of_death").value(movie.getDirector().getDateOfDeath().toString()))
                .andExpect(jsonPath("$.producers").isArray())
                .andExpect(jsonPath("$.producers.length()").value(movie.getProducers().size()))
                .andExpect(jsonPath("$.locations").value(movie.getLocations()))
                .andExpect(jsonPath("$.actors").isArray())
                .andExpect(jsonPath("$.actors.length()").value(movie.getActors().size()))
                .andExpect(jsonPath("$.created_at").isNotEmpty())
                .andExpect(jsonPath("$.updated_at").isNotEmpty());
    }

    @Test
    void should_return_status_not_found_when_movie_id_given_is_wrong() throws Exception {
        getMovie("41e7c4a8-ad00-4137-9c83-55edd8c58fe7")
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_bad_request_for_invalid_uuid() throws Exception {
        getMovie("not-a-uuid")
                .andExpect(status().isBadRequest());
    }
}
