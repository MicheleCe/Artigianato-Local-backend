package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, String> {

	List<Review> findByProductProductId(String productId);

	List<Review> findByUserUserId(String userId);

}
