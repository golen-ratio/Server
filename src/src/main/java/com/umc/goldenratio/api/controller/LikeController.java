package com.umc.goldenratio.api.controller;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.LikeEntity;
import com.umc.goldenratio.api.domain.entity.Users;
import com.umc.goldenratio.api.domain.repository.BoardRepository;
import com.umc.goldenratio.api.domain.repository.UsersRepository;
import com.umc.goldenratio.api.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final BoardRepository boardRepository;
    private final UsersRepository usersRepository;

    @PostMapping
    public ResponseEntity<LikeEntity> toggleLike(@RequestBody LikeEntity like) {
        Board board = boardRepository.findById(like.getBoard().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
        Users users = usersRepository.findById(like.getUsers().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid users ID"));
// toggleLike() 메서드는 사용자가 좋아요 버튼을 누를 때 해당 게시물 (Board)과 사용자 (Users)의 ID를 비교하여
// 이미 좋아요가 등록되어 있는지 확인
        LikeEntity existingLike = likeService.getLikeByBoardAndUsers(board, users);
        if (existingLike != null) {
            // 이미 좋아요가 등록되어 있는 경우 -> 좋아요 취소
            likeService.deleteLike(existingLike);
            return ResponseEntity.ok().build();
        } else {
            // 좋아요가 등록되어 있지 않은 경우 -> 좋아요 등록
            like.setBoard(board);
            like.setUsers(users);
            LikeEntity savedLike = likeService.saveLike(like);
            return ResponseEntity.ok(savedLike);
            //LikeEntity 의 게시물과 사용자 정보로 likeService.saveLike(like)를 호출하여 좋아요를 등록
        }
    }
}
