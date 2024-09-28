package com.airbnb.service;

import com.airbnb.dto.PropertyDto;
import com.airbnb.dto.ReviewDto;
import com.airbnb.entity.Property;
import com.airbnb.entity.PropertyUser;
import com.airbnb.entity.Review;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private final PropertyRepository propertyRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    public ResponseEntity<?> addReview(ReviewDto reviewDto, Long propertyId, PropertyUser user){

        Optional<Property> opProperty = propertyRepository.findById(propertyId);
        Property property = opProperty.get();
        Review review = mapToEntity(property, user, reviewDto);

        Review reviewByUser = reviewRepository.findReviewByUser(property, user);

        if(reviewByUser != null){
            return new ResponseEntity<>("review already giver", HttpStatus.BAD_REQUEST);
        }

        Review savedReview = reviewRepository.save(review);
        ReviewDto dto = mapTodto(savedReview);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    public List<Review> getUserReviews(PropertyUser user){
        List<Review> userReviews = reviewRepository.findByPropertyUser(user);
        return userReviews;
    }

    Review mapToEntity(Property property, PropertyUser user, ReviewDto reviewDto){
        Review review = new Review();
        review.setPropertyUser(user);
        review.setProperty(property);
        review.setContent(reviewDto.getContent());
        review.setRating(reviewDto.getRating());
        return review;
    }
    ReviewDto mapTodto(Review review){
        ReviewDto dto = new ReviewDto();
        dto.setProperty(review.getProperty());
        dto.setUser(review.getPropertyUser());
        dto.setContent(review.getContent());
        dto.setRating(review.getRating());
        return dto;
    }
}
