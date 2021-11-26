package com.bca.librarymanagementsystem.controller;

import com.bca.librarymanagementsystem.entity.Author;
import com.bca.librarymanagementsystem.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.bca.librarymanagementsystem.controller.LoginController.getLoggedInUser;

@Controller
public class AuthorController {

	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@RequestMapping("/authors")
	public String findAllAuthors(Model model, HttpSession session) {
		final List<Author> authors = authorService.findAllAuthors();

		model.addAttribute("authors", authors);
		model.addAttribute("user", getLoggedInUser(session));
		return "list-authors";
	}

	@RequestMapping("/author/{id}")
	public String findAuthorById(@PathVariable("id") Long id, Model model, HttpSession session) {
		final Author author = authorService.findAuthorById(id);

		model.addAttribute("author", author);
		model.addAttribute("user", getLoggedInUser(session));
		return "list-author";
	}

	@GetMapping("/addAuthor")
	public String showCreateForm(Author author, Model model, HttpSession session) {
		model.addAttribute("user", getLoggedInUser(session));
		return "add-author";
	}

	@RequestMapping("/add-author")
	public String createAuthor(Author author, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("user", getLoggedInUser(session));
			return "add-author";
		}

		authorService.createAuthor(author);
		model.addAttribute("author", authorService.findAllAuthors());
		model.addAttribute("user", getLoggedInUser(session));
		return "redirect:/authors";
	}

	@GetMapping("/updateAuthor/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model, HttpSession session) {
		final Author author = authorService.findAuthorById(id);

		model.addAttribute("author", author);
		model.addAttribute("user", getLoggedInUser(session));
		return "update-author";
	}

	@RequestMapping("/update-author/{id}")
	public String updateAuthor(@PathVariable("id") Long id, Author author, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			author.setId(id);
			model.addAttribute("user", getLoggedInUser(session));
			return "update-author";
		}

		authorService.updateAuthor(author);
		model.addAttribute("author", authorService.findAllAuthors());
		model.addAttribute("user", getLoggedInUser(session));
		return "redirect:/authors";
	}

	@RequestMapping("/remove-author/{id}")
	public String deleteAuthor(@PathVariable("id") Long id, Model model, HttpSession session) {
		authorService.deleteAuthor(id);

		model.addAttribute("author", authorService.findAllAuthors());
		model.addAttribute("user", getLoggedInUser(session));
		return "redirect:/authors";
	}

}
