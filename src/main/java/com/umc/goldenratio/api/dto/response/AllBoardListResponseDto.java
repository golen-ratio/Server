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
    private float averageScore;
    private int starCount;

}
