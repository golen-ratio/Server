package com.umc.goldenratio.api.dto.response;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Review;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto  {
    private Long id;
    private String title;
    private String content;
    private String mainImage;
    private String category;
    private double averageScore;
    private int alcohol;
    private int likesCount;
    private List<String> recipe;
    private List<Review> reviews;
    private Long userId;

    public static BoardDto from(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        boardDto.setMainImage(board.getMainImage());
        boardDto.setCategory(board.getCategory());
        boardDto.setAverageScore(board.getAverageScore());
        boardDto.setUserId(board.getUsers().getId());
        return boardDto;
    }

    // 칵테일 게시판 구조
    public static BoardDto fromCocktail(Board board) {
        BoardDto boardDto = from(board);
        boardDto.setAlcohol(board.getAlcohol());
        boardDto.setLikesCount(board.getLikes().size());
        boardDto.setRecipe(board.getRecipe());
        boardDto.setReviews(board.getReviews());
        return boardDto;
    }

    // 숙취해소 게시판 구조
    public static BoardDto fromHangover(Board board) {
        BoardDto boardDto = from(board);
        boardDto.setLikesCount(board.getLikes().size());
        boardDto.setRecipe(board.getRecipe());
        boardDto.setReviews(board.getReviews());
        return boardDto;
    }
}
