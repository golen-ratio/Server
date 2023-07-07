package com.umc.goldenratio.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Detail {
    @Id
    @GeneratedValue
    @Column(name = "datail_id")
    private Long id;

    @Column(name = "detail_sweet")
    private int sweet;

    @Column(name = "detail_alchol")
    private int alcohol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
}
