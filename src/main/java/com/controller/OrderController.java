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

import com.entity.Ordine;
import com.service.OrderService;

@CrossOrigin
@RequestMapping("/api")
@RestController
public class OrderController {
	@Autowired
	OrderService os;

	@GetMapping("/ordini")
	public ResponseEntity<List<Ordine>> get() {
		return os.get();
	}

	@PostMapping("/ordini")
	public ResponseEntity<Ordine> post(@RequestBody Ordine ordine) {
		return os.post(ordine);
	}

	@PatchMapping("/ordini")
	public ResponseEntity<Ordine> patch(@RequestBody Ordine ordine) {
		return os.patch(ordine);
	}

	@DeleteMapping("/ordini/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		return os.delete(id);
	}

}
