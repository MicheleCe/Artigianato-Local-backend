package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.LoginMessage;
import com.dto.LoginDTO;
import com.dto.UserDTO;
import com.entity.User;
import com.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<Object> addUser(UserDTO userDTO) {
		User user = new User(userDTO.getUserId(), userDTO.getUsername(), userDTO.getEmail(),
				this.passwordEncoder.encode(userDTO.getPassword()));
		if (userDTO.getEmail().contains("admin@")) {
			user.setRole("admin");
		} else {
			user.setRole("user");
		}
		if (userRepo.existsByEmail(userDTO.getEmail())) {
			System.out.println("Email is already registered.");
			return ResponseEntity.badRequest().body("Email is already registered.");
		}
		userRepo.save(user);
		return ResponseEntity.ok(user);
	}

	@Override
	public LoginMessage loginUser(LoginDTO loginDTO) {
		User user1 = userRepo.findByEmail(loginDTO.getEmail());
		if (user1 != null) {
			String password = loginDTO.getPassword();
			String encodedPassword = user1.getPassword();
			Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			if (isPwdRight) {
				Optional<User> user = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
				if (user.isPresent()) {
					return new LoginMessage("Login Success", true);
				} else {
					return new LoginMessage("Login Failed", false);
				}
			} else {
				return new LoginMessage("password Not Match", false);
			}
		} else {
			return new LoginMessage("Email not exits", false);
		}
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public void delete(String id) {
		try {
			userRepo.deleteById(id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

}
