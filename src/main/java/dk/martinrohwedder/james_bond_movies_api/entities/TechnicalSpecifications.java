package dk.martinrohwedder.james_bond_movies_api.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "technical_specifications")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class TechnicalSpecifications {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "runtime_in_minutes")
    private Integer runtimeInMinutes;

    @Column(name = "sound_mix")
    private String soundMix;

    @Column(name = "aspect_ratio")
    private String aspectRatio;

    @Column(name = "printed_film_format")
    private String printedFilmFormat;

    @OneToOne(mappedBy = "technicalSpecifications")
    private Movie movie;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
