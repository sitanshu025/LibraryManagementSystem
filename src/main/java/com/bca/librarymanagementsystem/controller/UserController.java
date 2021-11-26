package com.bca.librarymanagementsystem.controller;

import com.bca.librarymanagementsystem.entity.User;
import com.bca.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	public String findAllUsers(Model model) {
		final List<User> users = userService.findAllUsers();

		model.addAttribute("users", users);
		return "list-users";
	}

	@GetMapping("/addUser")
	public String showCreateForm(User user) {
		return "add-user";
	}

	@RequestMapping("/addUser")
	public String createUser(User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "add-user";
		}

		userService.createUser(user);
		model.addAttribute("users", userService.findAllUsers());
		return "redirect:/users";
	}

	@GetMapping("/updateUser/{email}")
	public String showUpdateForm(@PathVariable("email") String email, Model model) {
		final Optional<User> user = userService.findUserByEmail(email);

		model.addAttribute("user", user.get());
		return "update-user";
	}

	@RequestMapping("/updateUser/{email}")
	public String updateUser(@PathVariable("email") String email, User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setEmail(email);
			model.addAttribute("user", user);
			return "update-user";
		}

		userService.updateUser(user);
		model.addAttribute("User", userService.findAllUsers());
		return "redirect:/users";
	}

	@RequestMapping("/removeUser/{email}")
	public String deleteUser(@PathVariable("email") String email, Model model) {
		userService.deleteUser(email);

		model.addAttribute("User", userService.findAllUsers());
		return "redirect:/users";
	}

}
