package com.umc.goldenratio.api.service;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Likes;
import com.umc.goldenratio.api.domain.entity.Users;
import com.umc.goldenratio.api.domain.repository.LikeRepository;
import com.umc.goldenratio.api.dto.response.LikeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;

    public void toggleLike(Board board, Users users) {
        Likes existingLike = likeRepository.findByBoardAndUsers(board, users);
        if (existingLike != null) {
            likeRepository.deleteByBoardAndUsers(board, users);
        } else {
            Likes like = new Likes();
            like.setBoard(board);
            like.setUsers(users);
            likeRepository.save(like);
        }
    }

    public boolean isLikedByUsers(Board board, Users users) {
        return likeRepository.existsByBoardAndUsers(board, users);
    }
}
