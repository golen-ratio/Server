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
public class Like {
    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Like(Users users, Board board) {
        this.users = users;
        this.board = board;
    }
}
