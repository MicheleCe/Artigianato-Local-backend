package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.ReviewDTO;
import com.entity.Product;
import com.entity.Review;
import com.entity.User;
import com.repository.ProductRepository;
import com.repository.ReviewRepository;
import com.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository rr;

	@Autowired
	private ProductRepository pr;

	@Autowired
	private UserRepo ur;

	@Override
	public ResponseEntity<Review> createReview(ReviewDTO reviewDTO) {

		User user = ur.findById(reviewDTO.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + reviewDTO.getUserId()));

		Product product = pr.findById(reviewDTO.getProductId()).orElseThrow(
				() -> new EntityNotFoundException("Product not found with ID: " + reviewDTO.getProductId()));
		Review review = new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setComment(reviewDTO.getComment());

		rr.save(review);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@Override
	public List<Review> getReviewsForProduct(String productId) {
		return rr.findByProductProductId(productId);
	}

	@Override
	public List<Review> getReviewsByUser(String userId) {
		return rr.findByUserUserId(userId);
	}

	public void addReviewToProduct(Review review) {
		Product product = pr.findById(review.getProduct().getProductId())
				.orElseThrow(() -> new IllegalArgumentException("Product not found"));
		product.getReviews().add(review);

		pr.save(product);

		System.out.println(product);
	}

}
