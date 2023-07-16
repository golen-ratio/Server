package com.umc.goldenratio.api.service;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.LikeEntity;
import com.umc.goldenratio.api.domain.entity.Users;
import com.umc.goldenratio.api.domain.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public LikeEntity saveLike(LikeEntity like) {
        return likeRepository.save(like);
    }

    public LikeEntity getLikeByBoardAndUser(Board board, Users users) {
        return likeRepository.findByBoardAndUsers(board, users);
    }

    public LikeEntity getLikeByBoardAndUsers(Board board, Users users) {
        LikeEntity o = null;
        return o;
    }

    public void deleteLike(LikeEntity existingLike) {
    }
}
