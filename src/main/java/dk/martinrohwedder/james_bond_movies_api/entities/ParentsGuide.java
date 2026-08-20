package dk.martinrohwedder.james_bond_movies_api.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "parents_guides")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class ParentsGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "sex_and_nudity", length = 10)
    private String sexAndNudity;

    @Column(name = "violence_and_gore", length = 10)
    private String violenceAndGore;

    @Column(name = "profanity", length = 10)
    private String profanity;

    @Column(name = "alcohol_drugs_and_smoking", length = 10)
    private String alcoholDrugsAndSmoking;

    @Column(name = "frightening_and_intense_scenes", length = 10)
    private String frighteningAndIntenseScenes;

    @OneToOne(mappedBy = "parentsGuide")
    private Movie movie;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
