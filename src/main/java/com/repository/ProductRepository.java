package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

	List<Product> findByUserUserId(String userId);

}
