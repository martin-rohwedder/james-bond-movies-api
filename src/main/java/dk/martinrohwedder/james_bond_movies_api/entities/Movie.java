package dk.martinrohwedder.james_bond_movies_api.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "movies")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "movie_number")
    private Integer movieNumber;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "short_description", length = 1000)
    private String shortDescription;

    @Column(name = "long_description", columnDefinition = "TEXT")
    private String longDescription;

    @Column(name = "trailer_url", length = 100)
    private String trailerUrl;

    @Column(name = "world_premiere", length = 50)
    private String worldPremiere;

    @Column(name = "content_rating", length = 10)
    private String contentRating;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "parents_guide_id", referencedColumnName = "id")
    private ParentsGuide parentsGuide;

    @Builder.Default
    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReleaseDate> releaseDates = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "music_id", referencedColumnName = "id")
    private Music music;

    @Column(name = "james_bond_actor")
    private String jamesBondActor;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private Director director;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movies_producers",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id")
    )
    private List<Producer> producers = new ArrayList<>();

    @Column(name = "locations", length = 1000)
    private String locations;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors = new ArrayList<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movies_writers",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "writer_id")
    )
    private List<Writer> writers = new ArrayList<>();

    @Builder.Default
    @OneToMany(
            mappedBy = "movie",
            fetch =  FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Trivia> trivias = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "box_office_id", referencedColumnName = "id")
    private BoxOffice boxOffice;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "technical_specifications_id", referencedColumnName = "id")
    private TechnicalSpecifications technicalSpecifications;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /****************************************************************************************************
    /* Convenience method for adding bidirectional relationship between movie and release date entities.
     ****************************************************************************************************/

    public void addReleaseDate(ReleaseDate releaseDate) {
        releaseDates.add(releaseDate);
        releaseDate.setMovie(this);
    }

    /****************************************************************************************************
     /* Convenience method for adding bidirectional relationship between movie and producer entities.
     ****************************************************************************************************/

    public void addProducer(Producer producer) {
        producers.add(producer);
        producer.getMovies().add(this);
    }

    /****************************************************************************************************
     /* Convenience method for adding bidirectional relationship between movie and actor entities.
     ****************************************************************************************************/

    public void addActor(Actor actor) {
        actors.add(actor);
        actor.getMovies().add(this);
    }

    /****************************************************************************************************
     /* Convenience method for adding bidirectional relationship between movie and writer entities.
     ****************************************************************************************************/

    public void addWriter(Writer writer) {
        writers.add(writer);
        writer.getMovies().add(this);
    }

    /***************************************************************************************************************
     /* Convenience method for adding bidirectional relationship between movie and technical specification entities.
     ***************************************************************************************************************/

    public void addTechnicalSpecifications(TechnicalSpecifications technicalSpecifications) {
        this.technicalSpecifications = technicalSpecifications;
        technicalSpecifications.setMovie(this);
    }

    /**************************************************************************************************
     /* Convenience method for adding bidirectional relationship between movie and box office entities.
     **************************************************************************************************/

    public void addBoxOffice(BoxOffice boxOffice) {
        this.boxOffice = boxOffice;
        boxOffice.setMovie(this);
    }

    /*****************************************************************************************************
     /* Convenience method for adding bidirectional relationship between movie and parents guide entities.
     *****************************************************************************************************/

    public void addParentsGuide(ParentsGuide parentsGuide) {
        this.parentsGuide = parentsGuide;
        parentsGuide.setMovie(this);
    }

    /**********************************************************************************************
     /* Convenience method for adding bidirectional relationship between movie and trivia entities.
     **********************************************************************************************/

    public void addTrivia(Trivia trivia) {
        trivias.add(trivia);
        trivia.setMovie(this);
    }
}
