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
    private String category;

}

