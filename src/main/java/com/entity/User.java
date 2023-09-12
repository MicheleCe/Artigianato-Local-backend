package com.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String userId;
	@Column(name = "user_name", length = 255)
	private String username;
	private String role;
	@Column(unique = true)
	private String email;
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Ordine> orders;

	@OneToMany
	private List<Product> products;

	@OneToMany(mappedBy = "user")
	private List<Review> reviews;

	public User() {
	}

	public User(String userId, String username, String email, String password) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
	}

}
