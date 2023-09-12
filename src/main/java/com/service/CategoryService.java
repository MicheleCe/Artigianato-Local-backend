package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.entity.Category;

public interface CategoryService {

	public ResponseEntity<List<Category>> get();

	public ResponseEntity<String> delete(String id);

	public ResponseEntity<Category> post(Category category);

	public ResponseEntity<Category> patch(Category ctegory);
}
