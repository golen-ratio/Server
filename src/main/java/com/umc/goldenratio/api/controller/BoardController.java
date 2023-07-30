package com.umc.goldenratio.api.controller;

import com.umc.goldenratio.api.dto.request.CocktailRequestDto;
import com.umc.goldenratio.api.dto.request.HangoverRequestDto;
import com.umc.goldenratio.api.dto.response.AllBoardListResponseDto;
import com.umc.goldenratio.api.dto.response.BoardDto;
import com.umc.goldenratio.api.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @ApiOperation(value = "칵테일 게시판 생성")
    @PostMapping(value = "/golden-ratio/cocktail", produces = "application/text;charset = utf-8")
    public ResponseEntity<String> createBoard(Authentication authentication,
                                              @RequestBody CocktailRequestDto cocktailRequestDto){

        boardService.createCocktail(authentication, cocktailRequestDto);
        return ResponseEntity.ok().body("칵테일 게시글이 성공적으로 등록되었습니다.");
    }

    @ApiOperation(value = "숙취해소 게시판 생성")
    @PostMapping(value = "/golden-ratio/hangover", produces = "application/text;charset = utf-8")
    public ResponseEntity<String> createBoard(Authentication authentication,
                                              @RequestBody HangoverRequestDto hangoverRequestDto){

        boardService.createHangover(authentication, hangoverRequestDto);
        return ResponseEntity.ok().body("숙취해소 게시글이 성공적으로 등록되었습니다.");
    }

    @PutMapping(value = "/golden-ratio/cocktail/{board-id}", produces = "application/text;charset = utf-8")
    public ResponseEntity<String> updateBoard(Authentication authentication,
                                              @PathVariable("board-id") Long boardId,
                                              @RequestBody CocktailRequestDto cocktailRequestDto){

        boardService.updateCocktail(authentication, boardId, cocktailRequestDto);
        return ResponseEntity.ok().body("칵테일 게시글이 성공적으로 수정되었습니다.");
    }

    @PutMapping(value = "/golden-ratio/hangover/{board-id}", produces = "application/text;charset = utf-8")
    public ResponseEntity<String> updateBoard(Authentication authentication,
                                              @PathVariable("board-id") Long boardId,
                                              @RequestBody HangoverRequestDto hangoverRequestDto){

        boardService.updateHangover(authentication, boardId, hangoverRequestDto);
        return ResponseEntity.ok().body("숙취해소 게시글이 성공적으로 수정되었습니다.");
    }

    // 도수순서대로 정렬
    @GetMapping("/golden-ratio/cocktail/alchol")
    public ResponseEntity<List<BoardDto>> getBoardsSortedByAlcohol() {
        List<BoardDto> sortedBoards = boardService.getBoardsSortedByAlcohol();
        return ResponseEntity.ok(sortedBoards);
    }

    // 단맛순서대로 정렬
    @GetMapping("/golden-ratio/cocktail/sweet")
    public ResponseEntity<List<BoardDto>> getBoardsSortedBySweet() {
        List<BoardDto> sortedBoards = boardService.getBoardsSortedBySweet();
        return ResponseEntity.ok(sortedBoards);
    }

    // 칵테일 별점순 조회
    @GetMapping("/golden-ratio/cocktail/star")
    public ResponseEntity<List<BoardDto>> getCocktailBoardsSortedByStar(){
        List<BoardDto> sortedBoards = boardService.getCocktailBoardsSortedByStar();
        return ResponseEntity.ok(sortedBoards);
    }

    // 숙취해소 별점순 조회
    @GetMapping("/golden-ratio/hangover/star")
    public ResponseEntity<List<BoardDto>> getHangoverBoardsSortedByStar(){
        List<BoardDto> sortedBoards = boardService.getHangoverBoardsSortedByStar();
        return ResponseEntity.ok(sortedBoards);
    }

    // 칵테일 게시판 좋아요순 조회
    @GetMapping("/golden-ratio/cocktail/like")
    public ResponseEntity<List<BoardDto>> getCocktailBoardsSortedByLike() {
        List<BoardDto> sortedBoards = boardService.getBoardsSortedByLike("cocktail");
        return ResponseEntity.ok(sortedBoards);
    }

    // 숙취해소 게시판 좋아요순 조회
    @GetMapping("/golden-ratio/hangover/like")
    public ResponseEntity<List<BoardDto>> getHangoverBoardsSortedByLike() {
        List<BoardDto> sortedBoards = boardService.getBoardsSortedByLike("hangover");
        return ResponseEntity.ok(sortedBoards);
    }

    //칵테일 상세게시글 조회
    @ApiOperation(value = "칵테일 상세 게시글 조회")
    @GetMapping(value = "golden-ratio/cocktail/{board-id}", produces = "application/json;charset=utf-8")
    public ResponseEntity<BoardDto> getCocktailBoardDetails(@PathVariable("board-id") Long boardId) {
        BoardDto boardDto = boardService.getCocktailBoardDetails(boardId);
        if (boardDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boardDto);
    }

    //숙취해소 상세 게시글 조회
    @ApiOperation(value = "숙취해소 상세 게시글 조회")
    @GetMapping(value = "/golden-ratio/hangover/{board-id}", produces = "application/json;charset=utf-8")
    public ResponseEntity<BoardDto> getHangoverBoardDetails(@PathVariable("board-id") Long boardId) {
        BoardDto boardDto = boardService.getHangoverBoardDetails(boardId);
        if (boardDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boardDto);
    }

    @GetMapping("/golden-ratio/cocktail/all")
    public ResponseEntity<List<AllBoardListResponseDto>> getAllCocktailBoards(Authentication authentication) {
        List<AllBoardListResponseDto> allBoards = boardService.getAllCocktailBoards();
        return ResponseEntity.ok().body(allBoards);
    }

    @GetMapping("/golden-ratio/hangover/all")
    public ResponseEntity<List<AllBoardListResponseDto>> getAllHangoverBoards(Authentication authentication) {
        List<AllBoardListResponseDto> allBoards = boardService.getAllHangoverBoards();
        return ResponseEntity.ok().body(allBoards);
    }
     
}