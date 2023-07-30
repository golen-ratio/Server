package com.umc.goldenratio.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CocktailRequestDto {
    private String title;
    private String cocktailMainImageUrl;
    private String content;
    private String category;
    private int sweet;
    private int alcohol;
    private List<IngredientRequestDto> gradientList;
    private List<BalanceRequestDto> balanceList;

}
