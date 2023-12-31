package com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Ordine {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ordine_id")
	private String ordineId;

	@Column(name = "is_paid")
	private boolean isPaid;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "ordine_product", joinColumns = @JoinColumn(name = "ordine_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
