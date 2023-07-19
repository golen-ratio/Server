package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 게시판의 리뷰 조회
    @Query(value =
            "SELECT * " +
                    "FROM review " +
                    "WHERE board_id = :boardId",
            nativeQuery = true)
    List<Review> findByBoardId(@Param("boardId") Long boardId);

    // 사용자의 리뷰 조회
    @Query(value =
            "SELECT * " +
                    "FROM review " +
                    "WHERE users_id = :usersId",
            nativeQuery = true)
    List<Review> findByUsersId(@Param("usersId") Long usersId);
}
