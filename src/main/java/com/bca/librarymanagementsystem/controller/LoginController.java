package com.bca.librarymanagementsystem.controller;

import com.bca.librarymanagementsystem.entity.User;
import com.bca.librarymanagementsystem.entity.UserType;
import com.bca.librarymanagementsystem.service.AuthorService;
import com.bca.librarymanagementsystem.service.BookService;
import com.bca.librarymanagementsystem.service.CategoryService;
import com.bca.librarymanagementsystem.service.PublisherService;
import com.bca.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

	private static final String LOGGED_IN_USER_SESSEION_KEY = "LOGGED_IN_USER";
	private static final String USER_KEY = "user";

	private final BookService bookService;
	private final AuthorService authorService;
	private final CategoryService categoryService;
	private final PublisherService publisherService;
	private final UserService userService;

	@Autowired
	public LoginController(BookService bookService, AuthorService authorService, CategoryService categoryService,
						   PublisherService publisherService, UserService userService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
		this.userService = userService;
	}

	@PostMapping("/login")
	public String login(User user, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			return "login";
		}

		final Optional<User> userDataFromDb = userService.findUserByEmail(user.getEmail());
		if (userDataFromDb.isPresent() && userDataFromDb.get().getPassword().equals(user.getPassword())) {
			User userDataForSession = User.builder()
					.email(userDataFromDb.get().getEmail())
					.type(userDataFromDb.get().getType())
					.name(userDataFromDb.get().getName())
					.bookCount(userDataFromDb.get().getBookCount())
					.books(userDataFromDb.get().getBooks())
					.build();
			session.setAttribute(LOGGED_IN_USER_SESSEION_KEY, userDataForSession);
			return handleLoggedInUser(model, session);
		}

		model.addAttribute("errorMessage", "Invalid login credentials!!");
		return "login";
	}

	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.removeAttribute(LOGGED_IN_USER_SESSEION_KEY);
		model.addAttribute(USER_KEY, new User());
		return "login";
	}

	@GetMapping("/")
	public String getHomePage(Model model, HttpSession session) {
		if (session.getAttribute(LOGGED_IN_USER_SESSEION_KEY) != null) {
			return handleLoggedInUser(model, session);
		}
		model.addAttribute(USER_KEY, new User());
		return "login";
	}

	private String handleLoggedInUser(Model model, HttpSession session) {
		final User user = getLoggedInUser(session);
		if (user.getType() == UserType.USER) {
			model.addAttribute(USER_KEY, user);
			model.addAttribute("bookCount", bookService.findAllBooks().size());
			model.addAttribute("authorCount", authorService.findAllAuthors().size());
			model.addAttribute("categoryCount", categoryService.findAllCategories().size());
			model.addAttribute("publisherCount", publisherService.findAllPublishers().size());
			return "user-home";
		} else {
			model.addAttribute(USER_KEY, user);
			model.addAttribute("bookCount", bookService.findAllBooks().size());
			model.addAttribute("authorCount", authorService.findAllAuthors().size());
			model.addAttribute("categoryCount", categoryService.findAllCategories().size());
			model.addAttribute("publisherCount", publisherService.findAllPublishers().size());
			return "admin-home";
		}
	}

	public static User getLoggedInUser(HttpSession session) {
		final User user = (User) session.getAttribute(LOGGED_IN_USER_SESSEION_KEY);
		return user;
	}
}
