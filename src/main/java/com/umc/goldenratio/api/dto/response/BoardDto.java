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
    private BigDecimal averageScore;
    private int alcohol;
    private Long likes;
    private List<String> recipe;
    private List<Review> reviews;
    private long commentCount;

    public static BoardDto from(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        boardDto.setMainImage(board.getMainImage());
        boardDto.setCategory(board.getCategory());
        boardDto.setAverageScore(board.getAverageScore());
        return boardDto;
    }

    // 칵테일 게시판 구조
    public static BoardDto fromCocktail(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .mainImage(board.getMainImage())
                .category(board.getCategory())
                .averageScore(board.getAverageScore())
                .alcohol(board.getAlcohol())
                .likes((long) board.getLikes().size())
                .recipe(board.getRecipe())
                .reviews(board.getReviews())
                .commentCount(board.getCommentCount())
                .build();
    }

    // 숙취해소 게시판 구조
    public static BoardDto fromHangover(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .mainImage(board.getMainImage())
                .category(board.getCategory())
                .averageScore(board.getAverageScore())
                .likes((long) board.getLikes().size())
                .recipe(board.getRecipe())
                .reviews(board.getReviews())
                .commentCount(board.getCommentCount())
                .build();
    }

    

}
