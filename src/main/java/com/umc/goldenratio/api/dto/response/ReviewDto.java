package com.umc.goldenratio.api.dto.response;

import com.umc.goldenratio.api.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private Long id;
    private Long BoardId;
    private Long UserId;
    private String content;
    private int score;

    public static ReviewDto createReviewDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getBoard().getId(),
                review.getUsers().getId(),
                review.getContent(),
                review.getScore()
        );
    }
}
