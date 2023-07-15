package com.umc.goldenratio.api.service;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Review;
import com.umc.goldenratio.api.domain.entity.Users;
import com.umc.goldenratio.api.domain.repository.BoardRepository;
import com.umc.goldenratio.api.domain.repository.ReviewRepository;
import com.umc.goldenratio.api.domain.repository.UsersRepository;
import com.umc.goldenratio.api.dto.request.WriteReviewDto;
import com.umc.goldenratio.api.dto.response.ReviewDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BoardRepository boardRepository;
    private final UsersRepository usersRepository;
    public Review write(Long boardId,  WriteReviewDto writeReviewDto, String userId) {
        // 게시판 조회
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        if (boardOptional.isEmpty()) {
            throw new RuntimeException("게시판이 존재하지 않습니다.");
        }
        Board board = boardOptional.get();

        // 사용자 조회
        Optional<Users> usersOptional = usersRepository.findByUserId(userId);
        if (usersOptional.isEmpty()) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
        Users users = usersOptional.get();
        // 리뷰 작성
        Review review = Review.builder()
                .content(writeReviewDto.getContent())
                .score(writeReviewDto.getScore())
                .users(users)
                .board(board)
                .build();

        // 리뷰 저장
        Review savedReview = reviewRepository.save(review);

        return savedReview;
    }

    // 특정 게시판의 리뷰 조회
    public List<ReviewDto> inquire(Long boardId) {
        // 게시판 조회
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        if (boardOptional.isEmpty()) {
            throw new RuntimeException("게시판이 존재하지 않습니다.");
        }
        return reviewRepository.findByBoardId(boardId)
                .stream()
                .map(review -> ReviewDto.createReviewDto(review))
                .collect(Collectors.toList());
    }

    // 사용자의 리뷰 조회
    public List<ReviewDto> inquireBasedUser(String userId) {
        // 사용자 조회
        Optional<Users> usersOptional = usersRepository.findByUserId(userId);
        if (usersOptional.isEmpty()) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
        Users users = usersOptional.get();

        Long usersId = users.getId();

        return reviewRepository.findByUsersId(usersId)
                .stream()
                .map(review -> ReviewDto.createReviewDto(review))
                .collect(Collectors.toList());
    }
}
