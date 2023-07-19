package com.umc.goldenratio.api.domain.entity;

import com.umc.goldenratio.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_title")
    private String title;

    @Column(name = "board_content")
    private String content;

    @Column(name = "board_main_image")
    private String mainImage;

    @LastModifiedDate
    private LocalDateTime lastModifiedTime;

    @Column(name = "board_category")
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Detail> details = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Balance> balances = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mapping> mappings = new ArrayList<>();

    @Builder
    public Board(String title, String content, String mainImage, String category, Users users) {
        this.title = title;
        this.content = content;
        this.mainImage = mainImage;
        this.category = category;
        this.users = users;
    }

    public static Board toEntity(String title, String content, String mainImage, String category, Users users) {
        return Board.builder()
                .title(title)
                .content(content)
                .mainImage(mainImage)
                .category(category)
                .users(users)
                .build();
    }
}
