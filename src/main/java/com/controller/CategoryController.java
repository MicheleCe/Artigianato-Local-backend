package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Category;
import com.service.CategoryService;

@CrossOrigin
@RequestMapping("/api")
@RestController
public class CategoryController {

	@Autowired
	CategoryService cs;

	@GetMapping("/categories")
	public ResponseEntity<List<Category>> get() {
		return cs.get();
	}

	@PostMapping("/categories")
	public ResponseEntity<Category> post(@RequestBody Category category) {
		return cs.post(category);
	}

	@PatchMapping("/categories")
	public ResponseEntity<Category> patch(@RequestBody Category category) {
		return cs.patch(category);
	}

	@DeleteMapping("/categories/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		return cs.delete(id);
	}
}
