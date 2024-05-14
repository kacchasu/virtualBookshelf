package com.queerxdisasster.virtualbookshelf.controller;

import com.queerxdisasster.virtualbookshelf.entity.Review;
import com.queerxdisasster.virtualbookshelf.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review savedReview = reviewService.save(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    // Additional endpoints as needed
}