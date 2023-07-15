package com.umc.goldenratio.api.controller;

import com.umc.goldenratio.api.domain.entity.Review;
import com.umc.goldenratio.api.domain.entity.Users;
import com.umc.goldenratio.api.dto.request.WriteReviewDto;
import com.umc.goldenratio.api.dto.response.ReviewDto;
import com.umc.goldenratio.api.service.ReviewService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/golden-ratio")
public class ReviewController {
    private final ReviewService reviewService;

    @ApiOperation(value = "리뷰 작성")
    @PostMapping("/review/{boardId}")
    public ResponseEntity<Review> writeReview(@PathVariable Long boardId,
                                              @RequestBody WriteReviewDto writeReviewDto,
                                              Authentication authentication){

        // Users의 id를 얻음
        String userId = authentication.getName();

        Review writedReview = reviewService.write(boardId, writeReviewDto, userId);

        return ResponseEntity.status(HttpStatus.OK).body(writedReview);
    }

    // 특정 게시판의 리뷰들 조회
    @GetMapping("/review/{boardId}")
    public ResponseEntity<List<ReviewDto>> inquireReview(@PathVariable Long boardId){
        List<ReviewDto> reviews = reviewService.inquire(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(reviews);

    }
    // 사용자의 리뷰 조회
    @GetMapping("/review")
    public ResponseEntity<List<ReviewDto>> inquireReview(Authentication authentication){
        // Users의 id를 얻음
        String userId = authentication.getName();
        List<ReviewDto> reviews = reviewService.inquireBasedUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(reviews);

    }

}
