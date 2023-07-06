package com.umc.goldenratio.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    @Id
    @GeneratedValue
    @Column(name = "balance_id")
    private Long id;

    @Column(name = "balance_name")
    private String balanceName;

    @Column(name = "balance_number")
    @Nullable
    private int balanceNumber;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
