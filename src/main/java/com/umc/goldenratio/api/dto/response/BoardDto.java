package com.umc.goldenratio.api.dto.response;

import com.umc.goldenratio.api.domain.entity.Board;
import lombok.*;

<<<<<<< HEAD
import java.math.BigDecimal;

=======
>>>>>>> 9144d1d ([FEAT] : 칵테일 도수높은순 조회기능 구현)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String mainImage;
    private String category;
//    private int likecount;

    public static BoardDto from(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        boardDto.setMainImage(board.getMainImage());
        boardDto.setCategory(board.getCategory());
        boardDto.setAverageScore(board.getAverageScore());
//        boardDto.setLikecount(board.getLikes().size());
        return boardDto;
    }
}
