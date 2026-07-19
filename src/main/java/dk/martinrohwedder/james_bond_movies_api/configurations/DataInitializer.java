package dk.martinrohwedder.james_bond_movies_api.configurations;

import dk.martinrohwedder.james_bond_movies_api.entities.*;
import dk.martinrohwedder.james_bond_movies_api.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Configuration
public class DataInitializer {
    private final HashMap<String, Director> directors = new HashMap<>();
    private final HashMap<String, Producer> producers = new HashMap<>();
    private final HashMap<String, Actor> actors = new HashMap<>();

    @Bean
    CommandLineRunner initializeMovies(MovieRepository movieRepository) {
        return args -> {
            createDirectors();
            createProducers();
            createActors();

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
                                actors.get("Sean Connery").getName(),
                                directors.get("Terence Young"),
                                new Producer[]{
                                        producers.get("Harry Saltzman"),
                                        producers.get("Albert R. Broccoli")
                                },
                                "Jamaica; Pinewood Studios, Bucks, UK",
                                new Actor[]{
                                        actors.get("Sean Connery"),
                                        actors.get("Ursula Andress"),
                                        actors.get("Bernard Lee"),
                                        actors.get("Joseph Wiseman")
                                }
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
                                actors.get("Sean Connery").getName(),
                                directors.get("Terence Young"),
                                new Producer[]{
                                        producers.get("Harry Saltzman"),
                                        producers.get("Albert R. Broccoli")
                                },
                                "Istanbul, Turkey; Venice, Italy; Scotland and Pinewood Studios, UK",
                                new Actor[] {
                                        actors.get("Sean Connery"),
                                        actors.get("Robert Shaw"),
                                        actors.get("Lotte Lenya"),
                                        actors.get("Daniella Bianchi"),
                                        actors.get("Bernard Lee")
                                }
                        )
                ));
            }
        };
    }

    private Movie generateMovie(
            int movieNumber,
            String title,
            String shortDescription,
            String longDescription,
            String trailerUrl,
            String worldPremiere,
            ReleaseDate[] releaseDates,
            String musicTitle,
            String musicPerformer,
            String musicSongUrl,
            String jamesBondActor,
            Director director,
            Producer[] producers,
            String locations,
            Actor[] actors
    ) {
        Movie movie = Movie.builder()
                .movieNumber(movieNumber)
                .title(title)
                .shortDescription(shortDescription)
                .longDescription(longDescription)
                .trailerUrl(trailerUrl)
                .worldPremiere(worldPremiere)
                .music(generateMusic(musicTitle, musicPerformer, musicSongUrl))
                .jamesBondActor(jamesBondActor)
                .director(director)
                .locations(locations)
                .build();

        // Use convenience method for setting bidirectional relationships between Movie and Release Dates
        Arrays.stream(releaseDates)
                .forEach(movie::addReleaseDate);

        // Use convenience method for setting bidirectional relationships between Movies and Producers
        Arrays.stream(producers)
                .forEach(movie::addProducer);

        // Use convenience method for setting bidirectional relationships between Movies and Actors
        Arrays.stream(actors)
                .forEach(movie::addActor);

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

    private Producer generateProducer(String name, String biography, String nationality, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        return Producer.builder()
                .name(name)
                .biography(biography)
                .nationality(nationality)
                .dateOfBirth(dateOfBirth)
                .dateOfDeath(dateOfDeath)
                .build();
    }

    private void createProducers() {
        producers.put("Harry Saltzman", generateProducer(
                "Harry Saltzman",
                "Harry Saltzman was born on October 27, 1915 in Sherbrooke, Québec, Canada. He was a producer and production manager, known for Dr. No (1962), You Only Live Twice (1967) and Live And Let Die (1973). He was married to Tanya Morris, Jacqueline Colin and Adriana Ghinsberg. He died on September 28, 1994 in Neuilly-sur-Seine, Hauts-de-Seine, France.",
                "Canadian",
                LocalDate.of(1915, 10, 27),
                LocalDate.of(1994, 9, 28)
        ));
        producers.put("Albert R. Broccoli", generateProducer(
                "Albert R. Broccoli",
                "Albert Romolo Broccoli was born in Astoria, Queens (New York City) on April 5th, 1909. His mother and father, Cristina and Giovanni Broccoli, raised young Albert in New York on the family farm. The family was in the vegetable business, and Albert claimed one of his uncles brought the first broccoli seeds into the United States in the 1870's. Albert's cousin Pat DiCicco gave him the nickname 'Cubby' after a comic strip character named Kabibble. Cubby worked in a pharmacy and then as a coffin-maker, but a trip to see his cousin in Los Angeles gave him an ambition for film stardom. Pat was an actor's agent, and introduced Cubby to such stars as Randolph Scott, Cary Grant and Bob Hope.",
                "American",
                LocalDate.of(1909, 4, 5),
                LocalDate.of(1996, 6, 27)
        ));
    }

    private Actor generateActor(String name, String characterRole, String biography, String nationality, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        return Actor.builder()
                .name(name)
                .characterRole(characterRole)
                .biography(biography)
                .nationality(nationality)
                .dateOfBirth(dateOfBirth)
                .dateOfDeath(dateOfDeath)
                .build();
    }

    public void createActors() {
        actors.put("Sean Connery", generateActor(
                "Sean Connery",
                "James Bond",
                "The tall, handsome and muscular Scottish actor Sean Connery is best known as the original actor to portray James Bond in the hugely successful movie franchise, starring in seven films between 1962 and 1983. Some believed that such a career-defining role might leave him unable to escape it, but he proved the doubters wrong, becoming one of the most notable film actors of his generation, with a host of great movies to his name. This arguably culminated in his greatest acclaim in 1988, when Connery won the Academy Award for Best Supporting Actor for his role as an Irish cop in Th Untouchables (1987), stealing the thunder from the movie's principal star Kevin Costner. Connery was polled as 'The Greatest Living Scot' and 'Scotland's Greatest Living National Treasure.' In 1989, he was proclaimed 'Sexiest Man Alive' by People magazine, and in 1999, at age 69, he was proclaimed 'Sexiest Man of the Century.'",
                "Scottish",
                LocalDate.of(1930, 8, 25),
                LocalDate.of(2020, 10, 31)
        ));
        actors.put("Ursula Andress", generateActor(
                "Ursula Andress",
                "Honey Rider",
                "The quintessential jet-set Euro starlet, Ursula Andress was born in the Swiss canton of Berne on March 19, 1936, one of six children in a strict German Protestant family. Although often seeming icily aloof, a restless streak early demonstrated itself in her personality, and she had an impetuous desire to explore the world outside Switzerland. (For instance, she was tracked down by Interpol for running away from boarding school at 17 years old.) The stunning young woman found work as an art model in Rome and did walk-on parts in three quickie Italian pictures before coming to Hollywood in 1955 and getting nowhere professionally; a four-month fling with rising star James Dean brought her good publicity but not much else. That same year, still just 19, she met and had an affair with fading matinée idol John Derek, who left his wife Pati Behrs and two kids for Ursula even though she spoke almost no English at the time. In 1957 they eloped to Las Vegas, and the new bride put her acting aspirations on hold for a few years thereafter.",
                "Swiss",
                LocalDate.of(1936, 3, 19),
                null
        ));
        actors.put("Bernard Lee", generateActor(
                "Bernard Lee",
                "M.",
                "Best remembered as 'M' in the James Bond films, Bernard Lee was a popular character player in British films throughout the 1950s and 1960s. Born into a theatrical family, he made his stage debut at age six and later attended the Royal Academy of Dramatic Art. He first appeared on the West End stage in London in 1928, and continued to work in the theatre during the 1930s, taking only occasional film roles.",
                "British",
                LocalDate.of(1908, 1, 10),
                LocalDate.of(1981, 1, 16)
        ));
        actors.put("Joseph Wiseman", generateActor(
                "Joseph Wiseman",
                "Dr. No",
                "Joseph Wiseman was born on May 15, 1918 in Montreal, Quebec, Canada. He came to Broadway in the 1930s, where he was critically hailed for performances in Shakespeare's \"King Lear\", Clifford Odets' \"Golden Boy\" and Anton Chekhov's \"Uncle Vanya\". Motion pictures in which Wiseman has been seen include Detective Story (1951), starring Kirk Douglas, Viva Zapata! (1952) with Marlon Brando, The Garment Jungle (1957), The Night They Raided Minsky's (1968), The Valachi Papers (1972) and The Apprenticeship of Duddy Kravitz (1974) which brought him back to his native Canada for a co-starring role with Richard Dreyfuss.",
                "Canadian-American",
                LocalDate.of(1918, 5, 15),
                LocalDate.of(2009, 10, 19)
        ));
        actors.put("Robert Shaw", generateActor(
                "Robert Shaw",
                "Grant",
                "Robert Archibald Shaw was born on August 9, 1927, in Westhoughton, Lancashire, England, the eldest son of Doreen Nora (Avery), a nurse, and Thomas Archibald Shaw, a doctor. His paternal grandfather was Scottish, from Argyll. Shaw's mother, who was born in Piggs Peak, Swaziland, met his father while she was a nurse at a hospital in Truro, Cornwall. His father was an alcoholic and a manic depressive; he committed suicide when Robert was only 12. He had three sisters--Elisabeth, Joanna and Wendy--and one brother, Alexander.",
                "British",
                LocalDate.of(1927, 8, 9),
                LocalDate.of(1978, 8, 28)
        ));
        actors.put("Lotte Lenya", generateActor(
                "Lotte Lenya",
                "Rosa Klebb",
                "Lotte Lenya was a Tony Award-winning and Academy award-nominated actress and singer. While best remembered in the U.S. for her supporting role as Rosa Klebb in the classic Bond film From Russia With Love (1963), she is celebrated in Germany for her ground-breaking performances in the plays of Kurt Weill and Bertolt Brecht and her recordings of songs from those works.",
                "Austrain-American",
                LocalDate.of(1898, 10, 18),
                LocalDate.of(1981, 11, 27)
        ));
        actors.put("Daniella Bianchi", generateActor(
                "Daniella Bianchi",
                "Tatiana Romanova",
                "Daniela Bianchi is an Italian actress, best known for her role of Bond girl Tatiana Romanova in From Russia With Love (1963). She Finished 1st Runner Up in Miss Universe 1960 Competition, enough to get the attentions of Bond movie producers who chose her over 200 female prospects for the role of Tatiana Romanova.",
                "Italian",
                LocalDate.of(1942, 1, 31),
                null
        ));
    }
}
