package com.umc.goldenratio.api.dto.response;

import com.umc.goldenratio.api.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private Long id;
    private Long boardId;
    private Long userId;
    private String content;
    private double score;

    public static ReviewDto createReviewDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getBoard().getId(),
                review.getUsers().getId(),
                review.getContent(),
                review.getScore()
        );

    }
    public static ReviewDto from(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .boardId(review.getBoard().getId())
                .userId(review.getUsers().getId())
                .content(review.getContent())
                .score(review.getScore())
                .build();
    }
}

