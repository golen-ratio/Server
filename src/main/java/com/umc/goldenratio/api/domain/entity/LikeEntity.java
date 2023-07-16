package com.umc.goldenratio.api.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MEMBER")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public LikeEntity(Users users, Board board) {
        this.users = users;
        this.board = board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
