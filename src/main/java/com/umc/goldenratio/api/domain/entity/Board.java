package com.umc.goldenratio.api.domain.entity;

import com.umc.goldenratio.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Column(name = "total_score")
    private double totalScore;

    @Column(name = "average_score")
    private double averageScore;

    @Column(name = "likes_count")
    private int likesCount;

    @Column(name = "comment_count")
    private Long commentCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @ElementCollection
    private List<String> recipe;

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
    public Board(String title, String content, String mainImage, String category, Double averageScore, Double totalScore, Users users) {
        this.title = title;
        this.content = content;
        this.mainImage = mainImage;
        this.category = category;
        this.averageScore = averageScore;
        this.totalScore = totalScore;
        this.users = users;
    }

    public static Board toEntity(String title, String content, String mainImage, String category, Double averageScore, Double totalScore, Users users) {
        return Board.builder()
                .title(title)
                .content(content)
                .mainImage(mainImage)
                .category(category)
                .averageScore(averageScore)
                .totalScore(totalScore)
                .users(users)
                .build();
    }

    public void update(String title, String content, String mainImage, String category, Users users) {
        this.title = title;
        this.content = content;
        this.mainImage = mainImage;
        this.category = category;
        this.users = users;
    }

    // 별점 평균 구하는 함수
    public void updateAverageScore(double newScore, int allReviewCount) {
        totalScore += newScore;
        System.out.println("totalScore = " + totalScore);
        System.out.println("allReviewCount = " + allReviewCount);
        averageScore = totalScore / (double) allReviewCount;
        this.averageScore = Math.round(averageScore * 10.0) / 10.0;
    }
    // 좋아요 수 저장하는 함수
    public void updateLikesCount() {
        this.likesCount = likes.size();
    }

    public int getAlcohol() {
        if (details == null || details.isEmpty()) {
            return 0;
        }
        return details.get(0).getAlcohol();
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }
}
