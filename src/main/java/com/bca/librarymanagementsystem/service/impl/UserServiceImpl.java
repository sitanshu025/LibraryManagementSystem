package com.bca.librarymanagementsystem.service.impl;

import com.bca.librarymanagementsystem.entity.User;
import com.bca.librarymanagementsystem.exception.NotFoundException;
import com.bca.librarymanagementsystem.repository.UserRepository;
import com.bca.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Optional<User> findUserByEmail(String email) {
		return userRepository.findById(email);
	}

	@Override
	public void createUser(User User) {
		userRepository.save(User);
	}

	@Override
	public void updateUser(User User) {
		userRepository.save(User);
	}

	@Override
	public void deleteUser(String email) {
		final User User = userRepository.findById(email)
				.orElseThrow(() -> new NotFoundException(String.format("User not found with email %d", email)));

		userRepository.deleteById(User.getEmail());
	}

}
