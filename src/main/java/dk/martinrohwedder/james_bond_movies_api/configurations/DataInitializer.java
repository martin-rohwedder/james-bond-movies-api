package dk.martinrohwedder.james_bond_movies_api.configurations;

import dk.martinrohwedder.james_bond_movies_api.entities.Director;
import dk.martinrohwedder.james_bond_movies_api.entities.Movie;
import dk.martinrohwedder.james_bond_movies_api.entities.Music;
import dk.martinrohwedder.james_bond_movies_api.entities.ReleaseDate;
import dk.martinrohwedder.james_bond_movies_api.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer {
    private final HashMap<String, Director> directors = new HashMap<>();

    @Bean
    CommandLineRunner initializeMovies(MovieRepository movieRepository) {
        return args -> {
            createDirectors();

            if (movieRepository.count() == 0) {
                movieRepository.saveAll(List.of(
                        generateMovie(
                                1,
                                "Dr. No.",
                                "A resourceful British government agent seeks answers in a case involving the disappearance of a colleague and the disruption of the American space program.",
                                "Arriving in Jamaica to investigate the suspected murder of a fellow agent and his secretary, James Bond eludes several attempts on his life. With the help of CIA agent Felix Leiter and local fisherman Quarrel, Bond follows the sinister trail of Dr. No to his island Crab Key. Shortly after landing on the beach with Quarrel, Bond encounters alluring shell collector Honey Ryder. The three uninvited visitors are hunted down by Dr. No’s private army, who kill Quarrel and take Bond and Honey to Dr. No’s magnificent lair. Their megalomaniac host, Dr. No tells Bond that by utilising the nuclear laboratory on site, he plans to destroy the US Space program as his first move towards world domination. Bond outwits Dr. No who falls victim to his own scheme and dies. Rescuing Honey Ryder, Bond commandeers a motorboat and together they escape from Crab Key, seconds before it explodes, leaving the final devastation of Dr. No’s laboratory behind.",
                                "https://www.youtube.com/watch?v=H8u0962-baU",
                                "5 October 1962, London Pavilion Cinema, London",
                                new ReleaseDate[]{
                                        generateReleaseDate(LocalDate.of(1962, 10, 6), "United Kingdom", "UK"),
                                        generateReleaseDate(LocalDate.of(1963, 5, 8), "United States of America", "USA")
                                },
                                "James Bond Theme",
                                "John Barry Orchestra",
                                "https://www.youtube.com/watch?v=nJhz93idooI",
                                directors.get("Terence Young")
                        ),
                        generateMovie(
                                2,
                                "From Russia With Love",
                                "James Bond willingly inserts himself into an assassination plot involving a naive Russian beauty in order to retrieve a Soviet encryption device that was stolen by the evil SPECTRE organization.",
                                "James Bond is assigned by his superior M to help a young Russian girl Tatiana Romanova, who has declared her desire to defect from her job as a clerk in the Russian embassy in Istanbul with an invaluable Lektor cipher machine. Believing herself a willing tool of her government, Tatiana is actually the pawn of SPECTRE, a group of international criminals who plan to use the beautiful Russian girl to lure Bond to his death and to confound both the British and Russian Secret Service agencies. In the intriguing atmosphere of Istanbul, Bond is aided by Kerim Bey, the Turkish agent for the British Secret Service, whom Bond comes to respect and admire. After eluding several death traps in Istanbul, Bond and Tatiana escape aboard the Orient Express. SPECTRE has assigned their cold-blooded killer, Grant to kill and discredit the famed British agent, in hand-to-hand combat Bond triumphs over Grant in the close quarters of his train compartment, but the attempts on his life are by no means over. He later fights an unequal battle against a SPECTRE helicopter and makes a desperate dash across the Gulf of Venice in a speedboat chased by a horde of enemy agents. In Venice, he faces the final attempt on his life when Rosa Klebb, the master planner of the SPECTRE murder organisation, makes a personal bid to kill him.",
                                "https://www.youtube.com/watch?v=XbYsb9sThaY",
                                "10 October 1963, Odeon Leicester Square, London",
                                new ReleaseDate[]{
                                        generateReleaseDate(LocalDate.of(1963, 10, 10), "United Kingdom", "UK"),
                                        generateReleaseDate(LocalDate.of(1964, 4, 8), "United States of America", "USA")
                                },
                                "From Russia With Love",
                                "Matt Munro",
                                "https://www.youtube.com/watch?v=tee3Me7mgk0",
                                directors.get("Terence Young")
                        )
                ));
            }
        };
    }

    private Movie generateMovie(int movieNumber, String title, String shortDescription, String longDescription, String trailerUrl, String worldPremiere, ReleaseDate[] releaseDates, String musicTitle, String musicPerformer, String musicSongUrl, Director director) {
        Movie movie = Movie.builder()
                .movieNumber(movieNumber)
                .title(title)
                .shortDescription(shortDescription)
                .longDescription(longDescription)
                .trailerUrl(trailerUrl)
                .worldPremiere(worldPremiere)
                .music(generateMusic(musicTitle, musicPerformer, musicSongUrl))
                .director(director)
                .build();

        // Use convenience method for setting bidirectional relationships between Movie and Release Dates
        Arrays.stream(releaseDates)
                .forEach(movie::addReleaseDate);

        return movie;
    }

    private Music generateMusic(String title, String performer, String songUrl) {
        return Music.builder()
                .title(title)
                .performer(performer)
                .songUrl(songUrl)
                .build();
    }

    private Director generateDirector(String name, String biography, String nationality, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        return Director.builder()
                .name(name)
                .biography(biography)
                .nationality(nationality)
                .dateOfBirth(dateOfBirth)
                .dateOfDeath(dateOfDeath)
                .build();
    }

    private void createDirectors() {
        directors.put("Terence Young", generateDirector(
                "Terence Young",
                "Terence Young started his film career as a screenwriter in 1939, moving on to make his directorial debut in 1948 with Corridor of Mirrors starring Eric Portman. Over the course of 40 years (1948-1988) he directed 40 films, most notably three James Bond films -- Dr. No (1962), From Russia with Love (1963), and Thunderball (1965) -- as well as The Amorous Adventures of Moll Flanders (1965) starring Kim Novak, and Wait Until Dark (1967) starring Audrey Hepburn.",
                "British",
                LocalDate.of(1915, 6, 20),
                LocalDate.of(1994, 9, 7)
        ));
        directors.put("Guy Hamilton", generateDirector(
                "Guy Hamilton",
                "Guy Hamilton (1922–2016) was an acclaimed English film director best known for helming four classic James Bond films: Goldfinger (1964), Diamonds Are Forever (1971), Live and Let Die (1973), and The Man with the Golden Gun (1974). He directed 22 films across a prolific career that spanned from the 1950s to the 1980s",
                "British",
                LocalDate.of(1922, 9, 16),
                LocalDate.of(2016, 4, 20)
        ));
    }

    private ReleaseDate generateReleaseDate(LocalDate dateOfRelease, String country, String countryCode) {
        return ReleaseDate.builder()
                .dateOfRelease(dateOfRelease)
                .country(country)
                .countryCode(countryCode)
                .build();
    }
}
