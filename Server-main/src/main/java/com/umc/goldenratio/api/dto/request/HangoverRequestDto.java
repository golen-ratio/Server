package com.umc.goldenratio.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HangoverRequestDto {
    private String title;
    private String hangoverMainImageUrl;
    private String content;
    //숙취해소, 칵테일 
    private String category;
    private List<GradientRequestDto> gradientList;
}
