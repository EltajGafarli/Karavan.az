package com.karvan.entity.karavan;

import com.karvan.entity.karavan.Karavan;
import com.karvan.entity.karavan.KaravanAvadanliq;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "texniki_xususiyyetler")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TexnikiXususiyyetler {
    @Id
    private long id;

    @Column(length = 25)
    private String yanacaqNovu;

    @Column(length = 50)
    private String Istehlak;

    private Double yanacaqCeninHecmi;
    private Double avtomobilHundurluk;
    private Double avtomobilUzunlugu;
    private Double avtomobilEni;
    private Double temizSuCenininHecmi;
    private Double tullantiSUAnbarininHecmi;

    @OneToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    },
            mappedBy = "texnikiXususiyyetler"
    )
    private Karavan karavan;

    @ElementCollection(targetClass = KaravanAvadanliq.class, fetch = FetchType.EAGER)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<KaravanAvadanliq> texnikiXususiyyetler;

}
