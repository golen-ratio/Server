package com.umc.goldenratio.api.controller;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Users;
import com.umc.goldenratio.api.domain.repository.BoardRepository;
import com.umc.goldenratio.api.domain.repository.LikeRepository;
import com.umc.goldenratio.api.domain.repository.UsersRepository;
import com.umc.goldenratio.api.service.LikeService;
import com.umc.goldenratio.exception.CustomException;
import com.umc.goldenratio.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/golden-ratio")
@RequiredArgsConstructor
public class LikeController {

    private final BoardRepository boardRepository;
    private final UsersRepository usersRepository;
    private final LikeRepository likeRepository;
    private final LikeService likeService;

    @PostMapping("/like/{boardId}")
    public void toggleLike(Authentication authentication, @PathVariable Long boardId) {
        String userId = authentication.getName();
        Users users = usersRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USERID_NOT_FOUND));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));

        likeService.toggleLike(board, users);
    }
}

