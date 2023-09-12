package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dto.ReviewDTO;
import com.entity.Review;

public interface ReviewService {

	public ResponseEntity<Review> createReview(ReviewDTO review);

	public List<Review> getReviewsForProduct(String productId);

	public List<Review> getReviewsByUser(String userId);

	public void addReviewToProduct(Review review);

}
