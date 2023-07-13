package com.umc.goldenratio.api.dto.request;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WriteReviewDto {
    private String content;
    private int score;


}
