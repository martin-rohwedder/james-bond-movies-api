package dk.martinrohwedder.james_bond_movies_api.configurations;

import dk.martinrohwedder.james_bond_movies_api.entities.Movie;
import dk.martinrohwedder.james_bond_movies_api.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initializeMovies(MovieRepository movieRepository) {
        return args -> {
            if (movieRepository.count() == 0) {
                movieRepository.saveAll(List.of(
                        generateMovie(
                                "Dr. No.",
                                "A resourceful British government agent seeks answers in a case involving the disappearance of a colleague and the disruption of the American space program.",
                                "Arriving in Jamaica to investigate the suspected murder of a fellow agent and his secretary, James Bond eludes several attempts on his life. With the help of CIA agent Felix Leiter and local fisherman Quarrel, Bond follows the sinister trail of Dr. No to his island Crab Key. Shortly after landing on the beach with Quarrel, Bond encounters alluring shell collector Honey Ryder. The three uninvited visitors are hunted down by Dr. No’s private army, who kill Quarrel and take Bond and Honey to Dr. No’s magnificent lair. Their megalomaniac host, Dr. No tells Bond that by utilising the nuclear laboratory on site, he plans to destroy the US Space program as his first move towards world domination. Bond outwits Dr. No who falls victim to his own scheme and dies. Rescuing Honey Ryder, Bond commandeers a motorboat and together they escape from Crab Key, seconds before it explodes, leaving the final devastation of Dr. No’s laboratory behind.",
                                "https://www.youtube.com/watch?v=H8u0962-baU"
                        ),
                        generateMovie(
                                "From Russia With Love",
                                "James Bond willingly inserts himself into an assassination plot involving a naive Russian beauty in order to retrieve a Soviet encryption device that was stolen by the evil SPECTRE organization.",
                                "James Bond is assigned by his superior M to help a young Russian girl Tatiana Romanova, who has declared her desire to defect from her job as a clerk in the Russian embassy in Istanbul with an invaluable Lektor cipher machine. Believing herself a willing tool of her government, Tatiana is actually the pawn of SPECTRE, a group of international criminals who plan to use the beautiful Russian girl to lure Bond to his death and to confound both the British and Russian Secret Service agencies. In the intriguing atmosphere of Istanbul, Bond is aided by Kerim Bey, the Turkish agent for the British Secret Service, whom Bond comes to respect and admire. After eluding several death traps in Istanbul, Bond and Tatiana escape aboard the Orient Express. SPECTRE has assigned their cold-blooded killer, Grant to kill and discredit the famed British agent, in hand-to-hand combat Bond triumphs over Grant in the close quarters of his train compartment, but the attempts on his life are by no means over. He later fights an unequal battle against a SPECTRE helicopter and makes a desperate dash across the Gulf of Venice in a speedboat chased by a horde of enemy agents. In Venice, he faces the final attempt on his life when Rosa Klebb, the master planner of the SPECTRE murder organisation, makes a personal bid to kill him.",
                                "https://www.youtube.com/watch?v=XbYsb9sThaY"
                        )
                ));
            }
        };
    }

    private Movie generateMovie(String title, String shortDescription, String longDescription, String trailerUrl) {
        return Movie.builder()
                .title(title)
                .shortDescription(shortDescription)
                .longDescription(longDescription)
                .trailerUrl(trailerUrl)
                .build();
    }
}
