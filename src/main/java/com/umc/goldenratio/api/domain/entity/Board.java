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
    private BigDecimal totalScore;
    {
        // 초기화 블록을 사용하여 totalScore를 0으로 초기화
        totalScore = BigDecimal.ZERO;
    }

    @Column(name = "average_score")
    private BigDecimal averageScore;

<<<<<<< HEAD
    @Column(name = "likes_count")
    private int likesCount;
=======
    @Column(name = "comment_count")
    private Long commentCount;
>>>>>>> feature/posting/품바

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

    // 별점 평균 구하는 함수
    public void updateAverageScore(BigDecimal newScore, int allReviewCount) {
        if (reviews.isEmpty()) {
            averageScore = BigDecimal.ZERO;
        } else {
            totalScore = totalScore.add(newScore);
            System.out.println("리뷰 사이즈; "+ allReviewCount);
            BigDecimal reviewCount = BigDecimal.valueOf(allReviewCount-1);

            averageScore = totalScore.divide(reviewCount, 2, RoundingMode.HALF_EVEN);
        }
    }
<<<<<<< HEAD
    // 좋아요 수 저장하는 함수
    public void updateLikesCount() {
        this.likesCount = likes.size();
    }
=======

    public int getAlcohol() {
        if (details == null || details.isEmpty()) {
            return 0;
        }
        return details.get(0).getAlcohol();
    }

   
>>>>>>> feature/posting/품바
}
