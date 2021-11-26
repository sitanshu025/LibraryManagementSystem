package com.bca.librarymanagementsystem.service;

import com.bca.librarymanagementsystem.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

	List<User> findAllUsers();
	
	Optional<User> findUserByEmail(String email);

	void createUser(User user);

	void updateUser(User user);

	void deleteUser(String email);

}
