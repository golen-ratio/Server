package com.umc.goldenratio.api.controller;

import com.umc.goldenratio.api.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "게시판 생성")
    @PostMapping("/golden-ratio/board")
    public ResponseEntity<String> createBoard(Authentication authentication) {

        boardService.createBoard();
        return ResponseEntity.ok().body(authentication.getName());
    }
}
