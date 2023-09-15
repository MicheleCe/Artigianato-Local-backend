package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Image;

public interface ImageRepository extends JpaRepository<Image, String> {

	List<Image> findByProductProductId(String productId);
}
