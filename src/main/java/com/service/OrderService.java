package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.entity.Ordine;

public interface OrderService {

	public ResponseEntity<List<Ordine>> get();

	public ResponseEntity<String> delete(String id);

	public ResponseEntity<Ordine> post(Ordine order);

	public ResponseEntity<Ordine> patch(Ordine order);
}
