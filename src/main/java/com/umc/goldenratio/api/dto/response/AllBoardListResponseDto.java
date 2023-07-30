package com.umc.goldenratio.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllBoardListResponseDto {
    private Long BoardId;
    private String title;
    private String mainImageUrl;
    private double averageScore;
    private int likeCount;

    public static AllBoardListResponseDto of(Long BoardId, String title, String mainImageUrl, double averageScore, int likeCount) {
        return AllBoardListResponseDto.builder()
                .BoardId(BoardId)
                .title(title)
                .mainImageUrl(mainImageUrl)
                .averageScore(averageScore)
                .likeCount(likeCount)
                .build();
    }
}
