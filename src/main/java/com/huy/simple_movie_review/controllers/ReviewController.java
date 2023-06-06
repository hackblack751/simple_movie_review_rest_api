package com.huy.simple_movie_review.controllers;

import com.huy.simple_movie_review.dto.ReviewDto;
import com.huy.simple_movie_review.entities.Review;
import com.huy.simple_movie_review.services.ReviewService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createReview(@RequestBody ReviewDto reviewDto){
        Review review = reviewService.createReview(reviewDto);
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteReview(@RequestBody ReviewDto reviewDto){

        return ResponseEntity.ok("");
    }
}
