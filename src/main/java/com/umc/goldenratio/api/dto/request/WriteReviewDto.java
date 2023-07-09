package com.umc.goldenratio.api.dto.request;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WriteReviewDto {
    private Long BoardId;
    private Long UserId;
    private String content;
    private int score;

}
