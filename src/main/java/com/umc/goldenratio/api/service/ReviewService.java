package com.umc.goldenratio.api.service;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Review;
import com.umc.goldenratio.api.domain.entity.Users;
import com.umc.goldenratio.api.domain.repository.BoardRepository;
import com.umc.goldenratio.api.domain.repository.ReviewRepository;
import com.umc.goldenratio.api.domain.repository.UsersRepository;
import com.umc.goldenratio.api.dto.request.WriteReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private ReviewRepository reviewRepository;
    private final BoardRepository boardRepository;
    private final UsersRepository usersRepository;
    public Review write(Long boardId, WriteReviewDto writeReviewDto) {
        // 게시판 조회
        Board board  = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("대상 게시글이 없습니다."));
        Users users = usersRepository.findById(writeReviewDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        // review 엔티티 생성
        Review review = Review.builder()
                .content(writeReviewDto.getContent())
                .score(writeReviewDto.getScore())
                .board(board)
                .users(users)
                .build();

        return reviewRepository.save(review);
    }
}
