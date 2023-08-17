package com.umc.goldenratio.api.service;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Gradient;
import com.umc.goldenratio.api.domain.entity.Review;
import com.umc.goldenratio.api.domain.entity.Users;
import com.umc.goldenratio.api.domain.repository.BalanceRepository;
import com.umc.goldenratio.api.domain.repository.BoardRepository;
import com.umc.goldenratio.api.domain.repository.GradientRepository;
import com.umc.goldenratio.api.domain.repository.UsersRepository;
import com.umc.goldenratio.api.dto.request.CocktailRequestDto;
import com.umc.goldenratio.api.dto.request.HangoverRequestDto;
import com.umc.goldenratio.api.dto.response.AllBoardListResponseDto;
import com.umc.goldenratio.api.dto.response.BoardDto;
import com.umc.goldenratio.api.dto.response.IngredientResponseDto;
import com.umc.goldenratio.api.dto.response.StringResponseDto;
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
    private final GradientRepository gradientRepository;

    @Transactional
    public StringResponseDto createCocktail(Authentication authentication, CocktailRequestDto cocktailRequestDto) {
        Users users = usersRepository.findByUserId(authentication.getName()).orElseThrow(() -> new CustomException(ErrorCode.USERID_NOT_FOUND));
        Board board = Board.toEntity(cocktailRequestDto.getTitle(),
                cocktailRequestDto.getContent(),
                cocktailRequestDto.getCocktailMainImageUrl(),
                cocktailRequestDto.getCategory(),
                (double) 0,
                (double) 0,
                users);

        boardRepository.save(board);
        mappingService.save(cocktailRequestDto.getGradientList(), board);
        balanceService.save(cocktailRequestDto.getBalanceList(), board);
        detailService.save(cocktailRequestDto.getSweet(),cocktailRequestDto.getAlcohol(), board);
        return StringResponseDto.of("칵테일 게시글이 성공적으로 등록되었습니다.");
    }

    @Transactional
    public StringResponseDto createHangover(Authentication authentication, HangoverRequestDto hangoverRequestDto) {
        Users users = usersRepository.findByUserId(authentication.getName()).orElseThrow(() -> new CustomException(ErrorCode.USERID_NOT_FOUND));
        Board board = Board.toEntity(hangoverRequestDto.getTitle(),
                hangoverRequestDto.getContent(),
                hangoverRequestDto.getHangoverMainImageUrl(),
                hangoverRequestDto.getCategory(),
                (double) 0,
                (double) 0,
                users);

        boardRepository.save(board);
        mappingService.save(hangoverRequestDto.getGradientList(), board);
        return StringResponseDto.of("숙취해소 게시글이 성공적으로 등록되었습니다.");
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
            BoardDto boardDto;
            if (board.getCategory().equals("칵테일")) {
                boardDto = BoardDto.fromCocktail(board);
            } else if (board.getCategory().equals("숙취해소")) {
                boardDto = BoardDto.fromHangover(board);
            } else {
                continue; // 다른 카테고리의 경우 무시하고 다음 반복으로 이동
            }

            boardDto.setCreatedDate(board.getCreatedDate());
            boardDto.setLastModifiedTime(board.getLastModifiedTime());

            boardDtoList.add(boardDto);
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
    public List<BoardDto> getCocktailBoardsSortedByLike(String category) {
        List<Board> boards = boardRepository.findAllByCocktailOrderByLikesDesc();
        List<BoardDto> boardDtos = this.mapToBoardDtoList(boards);
        return boardDtos;
    }

    // 숙취해소 게시판 좋아요순서대로 정렬
    public List<BoardDto> getHangoverBoardsSortedByLike(String category) {
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

        BoardDto boardDto = BoardDto.fromCocktail(board); // BoardDto 생성

        boardDto.setCreatedDate(board.getCreatedDate()); // 생성 날짜 설정
        boardDto.setLastModifiedTime(board.getLastModifiedTime()); // 수정 날짜 설정

        return boardDto;
    }


    // board id 를 통해서 숙취해소의 구체적인 게시판을 가져옴
    public BoardDto getHangoverBoardDetails(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        if (board == null || !board.getCategory().equals("숙취해소")) {
            return null;
        }

            BoardDto boardDto = BoardDto.fromCocktail(board); // BoardDto 생성

            boardDto.setCreatedDate(board.getCreatedDate()); // 생성 날짜 설정
            boardDto.setLastModifiedTime(board.getLastModifiedTime()); // 수정 날짜 설정

            return boardDto;
        }


    @Transactional
    public StringResponseDto updateCocktail(Authentication authentication, Long boardId, CocktailRequestDto cocktailRequestDto) {
        Users users = usersRepository.findByUserId(authentication.getName()).orElseThrow(() -> new CustomException(ErrorCode.USERID_NOT_FOUND));
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        board.update(cocktailRequestDto.getTitle(),
                cocktailRequestDto.getContent(),
                cocktailRequestDto.getCocktailMainImageUrl(),
                cocktailRequestDto.getCategory(), users);
        boardRepository.save(board);
        mappingService.update(cocktailRequestDto.getGradientList(), board);
        balanceService.update(cocktailRequestDto.getBalanceList(), board);
        detailService.update(cocktailRequestDto.getSweet(),cocktailRequestDto.getAlcohol(), board);
        return StringResponseDto.of("칵테일 게시글이 성공적으로 수정되었습니다.");
    }

    @Transactional
    public StringResponseDto updateHangover(Authentication authentication, Long boardId, HangoverRequestDto hangoverRequestDto) {
        Users users = usersRepository.findByUserId(authentication.getName()).orElseThrow(() -> new CustomException(ErrorCode.USERID_NOT_FOUND));
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        board.update(hangoverRequestDto.getTitle(),
                hangoverRequestDto.getContent(),
                hangoverRequestDto.getHangoverMainImageUrl(),
                hangoverRequestDto.getCategory(), users);
        boardRepository.save(board);
        mappingService.update(hangoverRequestDto.getGradientList(), board);
        return StringResponseDto.of("숙취해소 게시글이 성공적으로 수정되었습니다.");
    }

    public List<AllBoardListResponseDto> getAllCocktailBoards() {
        List<Board> boards = boardRepository.findAllByCategoryOrderByCreatedDateDesc("칵테일");
        List<AllBoardListResponseDto> allBoardListResponseDtos = new ArrayList<>();
        for (Board board : boards) {
            int likeCount = board.getLikes().size();
            AllBoardListResponseDto allBoardListResponseDto = AllBoardListResponseDto.of(board.getId(), board.getTitle(), board.getMainImage(), board.getAverageScore(), likeCount);
            allBoardListResponseDtos.add(allBoardListResponseDto);
        }
        return allBoardListResponseDtos;
    }

    public List<AllBoardListResponseDto> getAllHangoverBoards() {
        List<Board> boards = boardRepository.findAllByCategoryOrderByCreatedDateDesc("숙취해소");
        List<AllBoardListResponseDto> allBoardListResponseDtos = new ArrayList<>();
        for(Board board : boards) {
            int likeCount = board.getLikes().size();
            AllBoardListResponseDto allBoardListResponseDto = AllBoardListResponseDto.of(board.getId(), board.getTitle(), board.getMainImage(), board.getAverageScore(), likeCount);
            allBoardListResponseDtos.add(allBoardListResponseDto);
        }
        return allBoardListResponseDtos;
    }

    public IngredientResponseDto searchGradient(String name) {
        Gradient gradient = gradientRepository.findByGradientName(name).orElseThrow(() -> new CustomException(ErrorCode.INGREDIENT_NOT_FOUND));
        return IngredientResponseDto.of(gradient.getGradientName(), gradient.getGradientImageUrl());
    }
}
