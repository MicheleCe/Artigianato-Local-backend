package com.entity;

import java.util.List;

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
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Ordine {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ordine_id")
	private String ordineId;

	private String phone;

	private String address;

	@Column(name = "is_paid")
	private boolean isPaid;

	@ManyToMany
	@JoinTable(name = "ordine_product", joinColumns = @JoinColumn(name = "ordine_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
