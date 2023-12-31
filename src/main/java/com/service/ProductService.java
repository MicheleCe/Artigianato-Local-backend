package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dto.ProductDTO;
import com.entity.Product;

public interface ProductService {

	public ResponseEntity<List<Product>> get();

	public ResponseEntity<String> delete(String id);

	public ResponseEntity<Product> patch(ProductDTO productDTO);

	public ResponseEntity<Product> post(ProductDTO productDTO);

	public List<Product> getProductsByUserId(String userId);

	public ResponseEntity<Product> findById(String productId);

	public ResponseEntity<List<Product>> getProductsByNameStartingWith(String name);

}
