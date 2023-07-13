package com.umc.goldenratio.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Balance(String balanceName, int balanceNumber, Board board) {
        this.balanceName = balanceName;
        this.balanceNumber = balanceNumber;
        this.board = board;
    }

    public static Balance toEntity(String balanceName, int balanceNumber, Board board){
        return Balance.builder()
                .balanceName(balanceName)
                .balanceNumber(balanceNumber)
                .board(board)
                .build();
    }

}
