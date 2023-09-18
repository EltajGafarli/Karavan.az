package com.karvan.entity.karavan;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "karavan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Karavan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long karavanID;

    @Column(length = 200)
    private String karavanName;

    private String location;


    private Short sernisinSayi;

    private Integer neqliyyatIli;

    private BigDecimal pricePerDay;

    private String karavanNovu;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "texnikiXususiyyetlerID")
    private TexnikiXususiyyetler texnikiXususiyyetler;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "karavan"
    )
    @ToString.Exclude
    private Set<Ratings> ratings;


    @Override
    public boolean equals(Object o) {
        Karavan karavan = (Karavan) o;
        return karavan.karavanID == karavanID
                && karavan.karavanName.equals(karavanName)
                && karavan.location.equals(location)
                && Objects.equals(karavan.neqliyyatIli, neqliyyatIli)
                && Objects.equals(karavan.pricePerDay, pricePerDay)
                && Objects.equals(karavan.sernisinSayi, sernisinSayi)
                && Objects.equals(texnikiXususiyyetler, karavan.texnikiXususiyyetler)
                && Objects.equals(karavanNovu, karavan.karavanNovu)
                && Objects.equals(createdAt, karavan.createdAt)
                && Objects.equals(updatedAt, karavan.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                karavanName,
                location,
                sernisinSayi,
                neqliyyatIli,
                pricePerDay,
                texnikiXususiyyetler,
                karavanNovu,
                ratings,
                createdAt,
                updatedAt
        );
    }

}
