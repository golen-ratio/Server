package com.umc.goldenratio.api.dto.response;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Review;
import com.umc.goldenratio.api.dto.request.BalanceRequestDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
    private int sweet;
    private int likesCount;
    private List<String> recipe;
    private List<ReviewDto> reviews; // 리뷰 목록을 ReviewDto로 변경
    private Long userId;
    private LocalDateTime createdDate; // 생성 날짜
    private LocalDateTime lastModifiedTime; // 수정 날짜
    private List<IngredientResponseDto> gradients;
    private List<BalanceRequestDto> balanceList;

    public static BoardDto from(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        boardDto.setMainImage(board.getMainImage());
        boardDto.setLikesCount(board.getLikesCount());
        boardDto.setCategory(board.getCategory());
        boardDto.setAverageScore(board.getAverageScore());
        boardDto.setUserId(board.getUsers().getId());
        boardDto.setCreatedDate(board.getCreatedDate());
        boardDto.setLastModifiedTime(board.getLastModifiedTime());
        return boardDto;
    }

    // 칵테일 게시판 구조
    public static BoardDto fromCocktail(Board board) {
        BoardDto boardDto = from(board);
        boardDto.setAlcohol(board.getAlcohol());
        boardDto.setLikesCount(board.getLikes().size());
        boardDto.setRecipe(board.getRecipe());
        boardDto.setSweet(board.getSweet());
        boardDto.setReviews(board.getReviews().stream()
                .map(ReviewDto::from)
                .collect(toList()));

        boardDto.setGradients(board.getGradient( ).stream( )
                .map(ingredient -> IngredientResponseDto.of(ingredient.getGradientName( ), ingredient.getGradientImageUrl( )))
                .collect(toList( )));

        boardDto.setBalanceList(board.getBalances( ).stream( )
                .map(balance -> {
                    BalanceRequestDto balanceDto = new BalanceRequestDto( );
                    balanceDto.setBalanceName(balance.getBalanceName( ));
                    balanceDto.setBalanceNum(balance.getBalanceNumber( ));
                    return balanceDto;
                })
                .collect(toList( )));

        boardDto.setReviews(board.getReviews( ).stream( )
                .map(ReviewDto::from)
                .collect(toList( )));

        boardDto.setCreatedDate(board.getCreatedDate( ));
        boardDto.setLastModifiedTime(board.getLastModifiedTime( ));
        boardDto.setMainImage(board.getUsers().getProfileImageUrl());
        return boardDto;


    }

    // 숙취해소 게시판 구조
    public static BoardDto fromHangover(Board board) {
        BoardDto boardDto = from(board);
        boardDto.setLikesCount(board.getLikes().size());
        boardDto.setRecipe(board.getRecipe());
        boardDto.setReviews(board.getReviews().stream()
                .map(ReviewDto::from)
                .collect(toList()));
        boardDto.setLikesCount(board.getLikes( ).size( ));

        boardDto.setGradients(board.getGradient( ).stream( )
                .map(ingredient -> IngredientResponseDto.of(ingredient.getGradientName( ), ingredient.getGradientImageUrl( )))
                .collect(toList( )));

        boardDto.setReviews(board.getReviews( ).stream( )
                .map(ReviewDto::from)
                .collect(toList( )));

        boardDto.setBalanceList(Collections.emptyList());
        boardDto.setCreatedDate(board.getCreatedDate( ));
        boardDto.setLastModifiedTime(board.getLastModifiedTime( ));
        boardDto.setMainImage(board.getUsers().getProfileImageUrl());
        return boardDto;
    }
}

