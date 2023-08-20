package com.umc.goldenratio.api.dto.response;

import com.umc.goldenratio.api.domain.entity.Board;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SortedBoardDto {
    private Long Boardid;
    private String title;
    private String mainImage;
    private double averageScore;
    private int likesCount;

    public static SortedBoardDto from(Board board) {
        SortedBoardDto sortedBoardDto = new SortedBoardDto();
        sortedBoardDto.setBoardid(board.getId());
        sortedBoardDto.setTitle(board.getTitle());
        sortedBoardDto.setMainImage(board.getMainImage());
        sortedBoardDto.setLikesCount(board.getLikesCount());
        sortedBoardDto.setAverageScore(board.getAverageScore());
        return sortedBoardDto;
    }

}