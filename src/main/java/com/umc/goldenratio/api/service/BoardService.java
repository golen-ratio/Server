package com.umc.goldenratio.api.service;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Users;
import com.umc.goldenratio.api.domain.repository.BalanceRepository;
import com.umc.goldenratio.api.domain.repository.BoardRepository;
import com.umc.goldenratio.api.domain.repository.UsersRepository;
import com.umc.goldenratio.api.dto.request.CocktailRequestDto;
import com.umc.goldenratio.api.dto.request.HangoverRequestDto;
import com.umc.goldenratio.api.dto.response.BoardDto;
import com.umc.goldenratio.exception.CustomException;
import com.umc.goldenratio.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UsersRepository usersRepository;
    private final MappingService mappingService;
    private final BalanceService balanceService;
    private final DetailService detailService;

    @Transactional
    public void createCocktail(Authentication authentication, CocktailRequestDto cocktailRequestDto) {
        Users users = usersRepository.findByUserId(authentication.getName()).orElseThrow(() -> new CustomException(ErrorCode.USERID_NOT_FOUND));
        Board board = Board.toEntity(cocktailRequestDto.getTitle(),
                cocktailRequestDto.getContent(),
                cocktailRequestDto.getCocktailMainImageUrl(),
                cocktailRequestDto.getCategory(), users);

        boardRepository.save(board);
        mappingService.save(cocktailRequestDto.getGradientList(), board);
        balanceService.save(cocktailRequestDto.getBalanceList(), board);
        detailService.save(cocktailRequestDto.getSweet(),cocktailRequestDto.getAlcohol(), board);
    }

    @Transactional
    public void createHangover(Authentication authentication, HangoverRequestDto hangoverRequestDto) {
        Users users = usersRepository.findByUserId(authentication.getName()).orElseThrow(() -> new CustomException(ErrorCode.USERID_NOT_FOUND));
        Board board = Board.toEntity(hangoverRequestDto.getTitle(),
                hangoverRequestDto.getContent(),
                hangoverRequestDto.getHangoverMainImageUrl(),
                hangoverRequestDto.getCategory(),
                users);

        boardRepository.save(board);
        mappingService.save(hangoverRequestDto.getGradientList(), board);
    }

    // 도수순서대로 정렬
    public List<BoardDto> getBoardsSortedByAlcohol() {
        List<Board> boards =  boardRepository.findAllByOrderByAlcoholDesc();
        List<BoardDto> boardDtos = this.mapToBoardDtoList(boards);
        return boardDtos;
    }

    // 단맛순서대로 정렬
    public List<BoardDto> getBoardsSortedBySweet() {
        List<Board> boards =  boardRepository.findAllByOrderBySweetlASC();
        List<BoardDto> boardDtos = this.mapToBoardDtoList(boards);
        return boardDtos;
    }

    private List<BoardDto> mapToBoardDtoList(List<Board> boards) {
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board : boards) {
            if (board.getCategory().equals("칵테일")) {
                boardDtoList.add(BoardDto.fromCocktail(board));
            } else if (board.getCategory().equals("숙취해소")) {
                boardDtoList.add(BoardDto.fromHangover(board));
            }
        }
        return boardDtoList;
    }

    // 칵테일 게시판 별점순서대로 정렬
    public List<BoardDto> getCocktailBoardsSortedByStar() {
        List<Board> boards =  boardRepository.findAllByCocktailOrderByAverageScoreDesc();
        List<BoardDto> boardDtos = this.mapToBoardDtoList(boards);
        return boardDtos;
    }

    // 숙취해소 게시판 별점순서대로 정렬
    public List<BoardDto> getHangoverBoardsSortedByStar() {
        List<Board> boards =  boardRepository.findAllByHangoverOrderByAverageScoreDesc();
        List<BoardDto> boardDtos = this.mapToBoardDtoList(boards);
        return boardDtos;
    }

    // 칵테일 게시판 좋아요순서대로 정렬
    public List<BoardDto> getBoardsSortedByLike(String category) {
        List<Board> boards = boardRepository.findAllByCocktailOrderByLikesDesc();
        List<BoardDto> boardDtos = this.mapToBoardDtoList(boards);
        return boardDtos;
    }

    // 숙취해소 게시판 좋아요순서대로 정렬
    public List<BoardDto> getHangoverBoardsSortedByLike() {
        List<Board> boards =  boardRepository.findAllByHangoverOrderByLikesDesc();
        List<BoardDto> boardDtos = this.mapToBoardDtoList(boards);
        return boardDtos;
    }
    // board id 를 통해서 칵테일의 구체적인 게시판을 가져옴
    public BoardDto getCocktailBoardDetails(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        if (board == null || !board.getCategory().equals("칵테일")) {
            return null; 
        }
        return BoardDto.fromCocktail(board);
    }

    // board id 를 통해서 숙취해소의 구체적인 게시판을 가져옴
    public BoardDto getHangoverBoardDetails(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        if (board == null || !board.getCategory().equals("숙취해소")) {
            return null; 
        }
        return BoardDto.fromHangover(board);
    }

}
