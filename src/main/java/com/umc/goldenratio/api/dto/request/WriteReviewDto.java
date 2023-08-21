package com.umc.goldenratio.api.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WriteReviewDto {
    private String content;
    private double score;


}
