package com.umc.goldenratio.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientResponseDto {
    private String gradientName;
    private String gradientImageUrl;

    public static IngredientResponseDto of(String gradientName, String gradientImageUrl) {
        return IngredientResponseDto.builder()
                .gradientName(gradientName)
                .gradientImageUrl(gradientImageUrl)
                .build();
    }
}
