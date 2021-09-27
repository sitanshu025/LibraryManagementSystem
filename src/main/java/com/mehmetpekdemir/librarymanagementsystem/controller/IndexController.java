package com.mehmetpekdemir.librarymanagementsystem.controller;

import com.mehmetpekdemir.librarymanagementsystem.service.AuthorService;
import com.mehmetpekdemir.librarymanagementsystem.service.BookService;
import com.mehmetpekdemir.librarymanagementsystem.service.CategoryService;
import com.mehmetpekdemir.librarymanagementsystem.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	private final BookService bookService;
	private final AuthorService authorService;
	private final CategoryService categoryService;
	private final PublisherService publisherService;

	@Autowired
	public IndexController(BookService bookService, AuthorService authorService, CategoryService categoryService,
						  PublisherService publisherService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("bookCount", bookService.findAllBooks().size());
		model.addAttribute("authorCount", authorService.findAllAuthors().size());
		model.addAttribute("categoryCount", categoryService.findAllCategories().size());
		model.addAttribute("publisherCount", publisherService.findAllPublishers().size());
		return "index";
	}
}
