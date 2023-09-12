package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.User;

public interface UserRepo extends JpaRepository<User, String> {

	Optional<User> findOneByEmailAndPassword(String email, String password);

	User findByEmail(String email);

	boolean existsByEmail(String email);

}
