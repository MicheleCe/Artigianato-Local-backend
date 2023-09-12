package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.LoginMessage;
import com.dto.LoginDTO;
import com.dto.UserDTO;
import com.entity.User;

public interface UserService {

	ResponseEntity<Object> addUser(UserDTO userDTO);

	LoginMessage loginUser(LoginDTO loginDTO);

	List<User> findAll();

	public void delete(String id);

	User findByEmail(String email);

}
