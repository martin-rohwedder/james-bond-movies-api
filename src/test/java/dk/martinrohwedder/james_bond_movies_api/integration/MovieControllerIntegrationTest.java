package dk.martinrohwedder.james_bond_movies_api.integration;

import dk.martinrohwedder.james_bond_movies_api.entities.Movie;
import dk.martinrohwedder.james_bond_movies_api.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieControllerIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private MovieRepository movieRepository;

    private int expectedMovieCount() {
        return (int) movieRepository.count();
    }

    @Override
    protected String baseUrl() {
        return "/api/movies";
    }

    @Test
    void should_return_all_movies_ordered_by_movie_number() throws Exception {
        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movie_number").value(1))
                .andExpect(jsonPath("$[0].title").value("Dr. No"))
                .andExpect(jsonPath("$[1].movie_number").value(2))
                .andExpect(jsonPath("$[1].title").value("From Russia With Love"))
                .andExpect(jsonPath("$.length()").value(expectedMovieCount()));
    }

    @Test
    void should_return_actors_and_producers_by_default() throws Exception {
        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].actors").isArray())
                .andExpect(jsonPath("$[0].producers").isArray());
    }

    @Test
    void should_return_technical_specifications_by_default() throws Exception {
        getAll()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].technical_specifications").isMap())
                .andExpect(jsonPath("$[0].technical_specifications.runtime_in_minutes").isNumber())
                .andExpect(jsonPath("$[0].technical_specifications.sound_mix").isString())
                .andExpect(jsonPath("$[0].technical_specifications.aspect_ratio").isString())
                .andExpect(jsonPath("$[0].technical_specifications.printed_film_format").isString());
    }

    @Test
    void should_return_same_result_as_default_when_query_parameters_are_false() throws Exception {
        getWithParams("excludeActors", "false", "excludeProducers", "false")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].actors").isArray())
                .andExpect(jsonPath("$[0].producers").isArray());
    }

    @Test
    void should_exclude_actors_from_movies_when_query_parameter_is_true() throws Exception {
        getWithParams("excludeActors", "true", "excludeProducers", "false")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].actors").isEmpty())
                .andExpect(jsonPath("$[0].producers").isArray());
    }

    @Test
    void should_exclude_producers_from_movies_when_query_parameter_is_true() throws Exception {
        getWithParams("excludeActors", "false", "excludeProducers", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].producers").isEmpty())
                .andExpect(jsonPath("$[0].actors").isArray());
    }

    @Test
    void should_exclude_producers_and_actors_from_movies_when_query_parameters_is_true() throws Exception {
        getWithParams("excludeActors", "true", "excludeProducers", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].producers").isEmpty())
                .andExpect(jsonPath("$[0].actors").isEmpty());
    }

    @Test
    void should_not_change_number_of_movies_when_excluding_actors() throws Exception {
        getWithParams("excludeActors", "true", "excludeProducers", "false")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expectedMovieCount()));
    }

    @Test
    void should_not_change_number_of_movies_when_excluding_producers() throws Exception {
        getWithParams("excludeActors", "false", "excludeProducers", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expectedMovieCount()));
    }

    @Test
    void should_not_change_number_of_movies_when_excluding_actors_and_producers() throws Exception {
        getWithParams("excludeActors", "true", "excludeProducers", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expectedMovieCount()));
    }

    @Test
    void should_keep_director_music_and_technical_specifications_when_excluding_actors_and_producers() throws Exception {
        getWithParams("excludeActors", "true", "excludeProducers", "true")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].director.name").exists())
                .andExpect(jsonPath("$[0].music.title").exists())
                .andExpect(jsonPath("$[0].technical_specifications.runtime_in_minutes").exists())
                .andExpect(jsonPath("$[0].actors").isEmpty())
                .andExpect(jsonPath("$[0].producers").isEmpty());
    }

    @Test
    void should_return_bad_request_for_invalid_boolean_parameter() throws Exception {
        getWithParams("excludeActors", "invalid")
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_movie_by_id() throws Exception {
        Movie movie = movieRepository.findAllByOrderByMovieNumberAsc().getFirst();

        getById(movie.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(movie.getTitle()))
                .andExpect(jsonPath("$.id").value(movie.getId().toString()));
    }

    @Test
    void should_return_complete_movie_structure_by_id() throws Exception {
        Movie movie = movieRepository.findAllByOrderByMovieNumberAsc().getFirst();

        getById(movie.getId())
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
                .andExpect(jsonPath("$.technical_specifications.runtime_in_minutes").value(movie.getTechnicalSpecifications().getRuntimeInMinutes()))
                .andExpect(jsonPath("$.technical_specifications.sound_mix").value(movie.getTechnicalSpecifications().getSoundMix()))
                .andExpect(jsonPath("$.technical_specifications.aspect_ratio").value(movie.getTechnicalSpecifications().getAspectRatio()))
                .andExpect(jsonPath("$.technical_specifications.printed_film_format").value(movie.getTechnicalSpecifications().getPrintedFilmFormat()))
                .andExpect(jsonPath("$.created_at").isNotEmpty())
                .andExpect(jsonPath("$.updated_at").isNotEmpty());
    }

    @Test
    void should_return_technical_specifications_by_id() throws Exception {
        Movie movie = movieRepository.findAllByOrderByMovieNumberAsc().getFirst();

        getById(movie.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.technical_specifications.runtime_in_minutes")
                        .value(movie.getTechnicalSpecifications().getRuntimeInMinutes()))
                .andExpect(jsonPath("$.technical_specifications.sound_mix")
                        .value(movie.getTechnicalSpecifications().getSoundMix()))
                .andExpect(jsonPath("$.technical_specifications.aspect_ratio")
                        .value(movie.getTechnicalSpecifications().getAspectRatio()))
                .andExpect(jsonPath("$.technical_specifications.printed_film_format")
                        .value(movie.getTechnicalSpecifications().getPrintedFilmFormat()));
    }

    @Test
    void should_return_status_not_found_when_movie_id_given_is_wrong() throws Exception {
        getById("41e7c4a8-ad00-4137-9c83-55edd8c58fe7")
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_bad_request_for_invalid_uuid() throws Exception {
        getById("not-a-uuid")
                .andExpect(status().isBadRequest());
    }
}
