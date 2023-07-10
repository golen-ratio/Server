package com.umc.goldenratio.api.controller;

import com.umc.goldenratio.api.domain.entity.Review;
import com.umc.goldenratio.api.dto.request.WriteReviewDto;
import com.umc.goldenratio.api.dto.response.ReviewDto;
import com.umc.goldenratio.api.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goldenratio")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review/{boardId}")
    public ResponseEntity<Review> writeReview(@PathVariable Long boardId,
                                              @RequestBody WriteReviewDto writeReviewDto){
        Review writedReview = reviewService.write(boardId, writeReviewDto);

        return ResponseEntity.status(HttpStatus.OK).body(writedReview);

    }

    @GetMapping("/review/{boardId}")
    public ResponseEntity<List<ReviewDto>> inquireReview(@PathVariable Long boardId){
        List<ReviewDto> reviews = reviewService.inquire(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(reviews);

    }

}
