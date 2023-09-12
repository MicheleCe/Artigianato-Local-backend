package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LoginMessage;
import com.dto.LoginDTO;
import com.dto.UserDTO;
import com.entity.User;
import com.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService us;

	@PostMapping("/save")
	public ResponseEntity<Object> saveUser(@RequestBody UserDTO UserDTO) {
		ResponseEntity<Object> id = us.addUser(UserDTO);
		return id;
	}

	@PostMapping(path = "/login")
	public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO) {
		LoginMessage loginResponse = us.loginUser(loginDTO);
		return ResponseEntity.ok(loginResponse);
	}

	@GetMapping("/getUsers")
	public List<User> getAppuntamenti() {
		return us.findAll();
	}

	@DeleteMapping("/deleteUser/{id}")
	public void Delete(@PathVariable String id) {
		us.delete(id);
	}

	@GetMapping("/getByEmail/{email}")
	public User findByEmail(@PathVariable String email) {
		return us.findByEmail(email);
	}

}
