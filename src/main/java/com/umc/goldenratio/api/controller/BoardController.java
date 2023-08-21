package com.umc.goldenratio.api.controller;

import com.umc.goldenratio.api.dto.request.CocktailRequestDto;
import com.umc.goldenratio.api.dto.request.HangoverRequestDto;
import com.umc.goldenratio.api.dto.response.*;
import com.umc.goldenratio.api.dto.response.AllBoardListResponseDto;
import com.umc.goldenratio.api.dto.response.BoardDto;
import com.umc.goldenratio.api.dto.response.IngredientResponseDto;
import com.umc.goldenratio.api.dto.response.StringResponseDto;
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
    @PostMapping(value = "/golden-ratio/cocktail")
    public ResponseEntity<StringResponseDto> createBoard(Authentication authentication,
                                                         @RequestBody CocktailRequestDto cocktailRequestDto){

        StringResponseDto stringResponseDto = boardService.createCocktail(authentication, cocktailRequestDto);
        return ResponseEntity.ok().body(stringResponseDto);
    }

    @ApiOperation(value = "숙취해소 게시판 생성")
    @PostMapping(value = "/golden-ratio/hangover")
    public ResponseEntity<StringResponseDto> createBoard(Authentication authentication,
                                                         @RequestBody HangoverRequestDto hangoverRequestDto){

        StringResponseDto stringResponseDto = boardService.createHangover(authentication, hangoverRequestDto);
        return ResponseEntity.ok().body(stringResponseDto);
    }

    @PutMapping(value = "/golden-ratio/cocktail/{board-id}")
    public ResponseEntity<StringResponseDto> updateBoard(Authentication authentication,
                                                         @PathVariable("board-id") Long boardId,
                                                         @RequestBody CocktailRequestDto cocktailRequestDto){

        StringResponseDto stringResponseDto = boardService.updateCocktail(authentication, boardId, cocktailRequestDto);
        return ResponseEntity.ok().body(stringResponseDto);
    }

    @PutMapping(value = "/golden-ratio/hangover/{board-id}")
    public ResponseEntity<StringResponseDto> updateBoard(Authentication authentication,
                                                         @PathVariable("board-id") Long boardId,
                                                         @RequestBody HangoverRequestDto hangoverRequestDto){

        StringResponseDto stringResponseDto = boardService.updateHangover(authentication, boardId, hangoverRequestDto);
        return ResponseEntity.ok().body(stringResponseDto);
    }

    // 도수순서대로 정렬
    @GetMapping("/golden-ratio/cocktail/alchol")
    public ResponseEntity<List<SortedBoardDto>> getBoardsSortedByAlcohol() {
        List<SortedBoardDto> sortedBoards = boardService.getBoardsSortedByAlcohol();
        return ResponseEntity.ok(sortedBoards);
    }

    // 단맛순서대로 정렬
    @GetMapping("/golden-ratio/cocktail/sweet")
    public ResponseEntity<List<SortedBoardDto>> getBoardsSortedBySweet() {
        List<SortedBoardDto> sortedBoards = boardService.getBoardsSortedBySweet();
        return ResponseEntity.ok(sortedBoards);
    }

    // 칵테일 별점순 조회
    @GetMapping("/golden-ratio/cocktail/star")
    public ResponseEntity<List<SortedBoardDto>> getCocktailBoardsSortedByStar(){
        List<SortedBoardDto> sortedBoards = boardService.getCocktailBoardsSortedByStar();
        return ResponseEntity.ok(sortedBoards);
    }

    // 숙취해소 별점순 조회
    @GetMapping("/golden-ratio/hangover/star")
    public ResponseEntity<List<SortedBoardDto>> getHangoverBoardsSortedByStar(){
        List<SortedBoardDto> sortedBoards = boardService.getHangoverBoardsSortedByStar();
        return ResponseEntity.ok(sortedBoards);
    }

    // 칵테일 게시판 좋아요순 조회
    @GetMapping("/golden-ratio/cocktail/like")
 
    public ResponseEntity<List<SortedBoardDto>> getCocktailBoardsSortedByLike() {
        List<SortedBoardDto> sortedBoards = boardService.getCocktailBoardsSortedByLike("cocktail");
        return ResponseEntity.ok(sortedBoards);
    }

    // 숙취해소 게시판 좋아요순 조회
    @GetMapping("/golden-ratio/hangover/like")
    public ResponseEntity<List<SortedBoardDto>> getHangoverBoardsSortedByLike() {
        List<SortedBoardDto> sortedBoards = boardService.getHangoverBoardsSortedByLike("hangover");
        return ResponseEntity.ok(sortedBoards);
    }

    //칵테일 상세게시글 조회
    @ApiOperation(value = "칵테일 상세 게시글 조회")
    @GetMapping(value = "/golden-ratio/cocktail/{board-id}", produces = "application/json;charset=utf-8")
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

    @GetMapping("/golden-ratio/search")
    public ResponseEntity<IngredientResponseDto> searchGradient(Authentication authentication, @RequestParam String name) {
        IngredientResponseDto ingredientResponseDto = boardService.searchGradient(name);
        return ResponseEntity.ok().body(ingredientResponseDto);
    }

}

