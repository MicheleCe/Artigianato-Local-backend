package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.OrderDTO;
import com.entity.Ordine;
import com.entity.Product;
import com.entity.User;
import com.repository.OrderRepository;
import com.repository.ProductRepository;
import com.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository or;

	@Autowired
	private ProductRepository pr;

	@Autowired
	private UserRepo ur;

	@Override
	public ResponseEntity<List<Ordine>> get() {
		return new ResponseEntity<>(or.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> delete(String id) {
		try {
			or.deleteById(id);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Ordine> post(OrderDTO orderDTO) {
		Ordine order = new Ordine();
		System.out.println("entro order service" + orderDTO);
		try {
			User user = ur.findById(orderDTO.getUserId()).orElse(null);
			if (user == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			order.setUser(user);
			List<Product> productList = new ArrayList<>();
			for (String productId : orderDTO.getProductIds()) {
				Product product = pr.findById(productId)
						.orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
				productList.add(product);
			}
			order.setProducts(productList);
			or.save(order);
			for (Product product : productList) {
				pr.save(product);
			}
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Ordine> patch(Ordine order) {

		try {
			or.save(order);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (NoSuchElementException | IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
