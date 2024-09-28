package com.airbnb.controller;

import com.airbnb.dto.ReviewDto;
import com.airbnb.entity.PropertyUser;
import com.airbnb.entity.Review;
import com.airbnb.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/addReview/{propertyId}")
    public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDto,
                                               @AuthenticationPrincipal PropertyUser user,
                                               @PathVariable Long propertyId
                                               ){
        ResponseEntity<?> responseEntity = reviewService.addReview(reviewDto, propertyId, user);
        return new ResponseEntity<>(responseEntity, HttpStatus.CREATED);
    }

    @GetMapping("/userReviews")
    public ResponseEntity<List<Review>> getUserReviews(@AuthenticationPrincipal PropertyUser user){
        List<Review> userReviews = reviewService.getUserReviews(user);
        return new ResponseEntity<>(userReviews,HttpStatus.OK);
    }
}
