package com.umc.goldenratio.api.controller;

import com.umc.goldenratio.api.dto.request.CocktailRequestDto;
import com.umc.goldenratio.api.dto.request.HangoverRequestDto;
import com.umc.goldenratio.api.dto.response.BoardDto;
import com.umc.goldenratio.api.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
