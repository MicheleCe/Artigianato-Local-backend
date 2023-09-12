package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ReviewDTO;
import com.entity.Review;
import com.service.ReviewService;

@CrossOrigin
@RestController
@RequestMapping("api/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping("/create")
	public ResponseEntity<String> createReview(@RequestBody ReviewDTO reviewDTO) {
		ResponseEntity<Review> createdReview = reviewService.createReview(reviewDTO);
		return ResponseEntity.ok("Review created successfully with ID: " + createdReview);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getReviewsForProduct(@PathVariable String productId) {
		List<Review> reviews = reviewService.getReviewsForProduct(productId);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Review>> getReviewsByUser(@PathVariable String userId) {
		List<Review> reviews = reviewService.getReviewsByUser(userId);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

}
