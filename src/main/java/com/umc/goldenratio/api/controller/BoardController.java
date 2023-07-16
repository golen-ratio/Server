package com.umc.goldenratio.api.controller;

import com.umc.goldenratio.api.dto.request.CocktailRequestDto;
import com.umc.goldenratio.api.dto.request.HangoverRequestDto;
import com.umc.goldenratio.api.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping(value = "/golden-ratio/hangover", produces = "application/text;charset=utf-8")
    public ResponseEntity<String> createHangoverBoard(Authentication authentication,
                                                     @RequestBody HangoverRequestDto hangoverRequestDto) {
        boardService.createHangover(authentication, hangoverRequestDto);
        return ResponseEntity.ok().body("숙취해소 게시글이 성공적으로 등록되었습니다.");
    }

}
