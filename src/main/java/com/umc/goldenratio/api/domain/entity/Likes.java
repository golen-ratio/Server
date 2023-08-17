package com.umc.goldenratio.api.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    public void setBoard(Board board) {
        if (this.board != null) {
            this.board.getLikes().remove(this);
        }
        this.board = board;
        if (board != null) {
            board.getLikes().add(this); // Add the like
            board.updateLikesCount(); // Update
        }
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
