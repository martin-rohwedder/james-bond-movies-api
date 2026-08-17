package dk.martinrohwedder.james_bond_movies_api.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "box_office")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class BoxOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "budget_usd")
    private Long budgetUsd;

    @Column(name = "gross_revenue_us_and_canada_usd")
    private Long grossRevenueUsAndCanadaUsd;

    @Column(name = "gross_revenue_worldwide_usd")
    private Long grossRevenueWorldwideUsd;

    @OneToOne(mappedBy = "boxOffice")
    private Movie movie;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
