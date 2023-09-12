package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.ProductDTO;
import com.entity.Category;
import com.entity.Image;
import com.entity.Product;
import com.repository.CategoryRepository;
import com.repository.ImageRepository;
import com.repository.ProductRepository;
import com.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository pr;

	@Autowired
	private CategoryRepository cr;

	@Autowired
	private ImageRepository ir;

	@Autowired
	private UserRepo ur;

	@Override
	public ResponseEntity<List<Product>> get() {
		return new ResponseEntity<>(pr.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> delete(String id) {
		try {
			pr.deleteById(id);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public List<Product> getProductsByUserId(String userId) {
		List<Product> products = pr.findByUserUserId(userId);
		if (products.isEmpty()) {
			return products;
		}
		return products;
	}

	@Override
	public ResponseEntity<Product> patch(Product product) {

		try {
			pr.save(product);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (NoSuchElementException | IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Product> post(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		product.setUser(ur.findById(productDTO.getUserId()).get());

		List<Category> categories = new ArrayList<>();
		for (String categoryId : productDTO.getCategoryId()) {
			Category category = cr.findById(categoryId)
					.orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + categoryId));
			categories.add(category);
			category.getProducts().add(product);

			cr.save(category);
		}
		product.setCategories(categories);

		pr.save(product);

		List<Image> images = new ArrayList<>();
		for (byte[] imageData : productDTO.getImages()) {
			Image image = new Image();
			image.setImageData(imageData);
			image.setProduct(product);
			images.add(image);
			product.getImages().add(image);
		}
		ir.saveAll(images);
		pr.save(product);

		return new ResponseEntity<>(product, HttpStatus.OK);
	}

}
