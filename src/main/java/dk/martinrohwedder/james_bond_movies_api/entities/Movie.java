package dk.martinrohwedder.james_bond_movies_api.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "long_description", columnDefinition = "TEXT")
    private String longDescription;

    @Column(name = "trailer_url", length = 100)
    private String trailerUrl;

    @Column(name = "world_premiere", length = 50)
    private String worldPremiere;

    @Builder.Default
    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReleaseDate> releaseDates = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "music_id", referencedColumnName = "id")
    private Music music;

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
    private Set<Producer> producers = new HashSet<>();

    @Column(name = "locations", length = 1000)
    private String locations;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();

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
}
