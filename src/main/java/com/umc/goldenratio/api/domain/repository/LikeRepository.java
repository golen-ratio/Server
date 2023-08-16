package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Likes;
import com.umc.goldenratio.api.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
    Likes findByBoardAndUsers(Board board, Users users);

    boolean existsByBoardAndUsers(Board board, Users users);

    void deleteByBoardAndUsers(Board board, Users users);
}
