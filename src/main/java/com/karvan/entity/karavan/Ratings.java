package com.karvan.entity.karavan;

import com.karvan.entity.karavan.Karavan;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ratings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ratings {

    @Id
    private long id;
    private int rating;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "karavan_id")
    private Karavan karavan;
}
