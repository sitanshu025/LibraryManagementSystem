package com.bca.librarymanagementsystem.controller;

import com.bca.librarymanagementsystem.entity.Book;
import com.bca.librarymanagementsystem.service.AuthorService;
import com.bca.librarymanagementsystem.service.BookService;
import com.bca.librarymanagementsystem.service.CategoryService;
import com.bca.librarymanagementsystem.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
public class BookController {

	private final BookService bookService;
	private final AuthorService authorService;
	private final CategoryService categoryService;
	private final PublisherService publisherService;

	@Autowired
	public BookController(BookService bookService, AuthorService authorService, CategoryService categoryService,
			PublisherService publisherService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
	}

	@RequestMapping("/books")
	public String findAllBooks(Model model, HttpSession session) {
		final List<Book> books = bookService.findAllBooks();

		model.addAttribute("books", books);
		model.addAttribute("user", getLoggedInUser(session));
		return "list-books";
	}

	@RequestMapping("/searchBook")
	public String searchBook(@Param("keyword") String keyword, Model model, HttpSession session) {
		final List<Book> books = bookService.searchBooks(keyword);

		model.addAttribute("books", books);
		model.addAttribute("keyword", keyword);
		model.addAttribute("user", getLoggedInUser(session));
		return "list-books";
	}

	@GetMapping("/searchBook/{userEmail}")
	public String searchBookByUser(@PathVariable("userEmail") String email, Model model, HttpSession session) {
		final List<Book> books = bookService.findBookByUser(email);

		model.addAttribute("books", books);
		model.addAttribute("user", getLoggedInUser(session));
		return "list-books";
	}

	@RequestMapping("/book/{id}")
	public String findBookById(@PathVariable("id") Long id, Model model, HttpSession session) {
		final Book book = bookService.findBookById(id);

		model.addAttribute("book", book);
		model.addAttribute("user", getLoggedInUser(session));
		return "list-book";
	}

	@GetMapping("/add")
	public String showCreateForm(Book book, Model model, HttpSession session) {
		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("authors", authorService.findAllAuthors());
		model.addAttribute("publishers", publisherService.findAllPublishers());
		model.addAttribute("user", getLoggedInUser(session));
		return "add-book";
	}

	@RequestMapping("/add-book")
	public String createBook(Book book, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("user", getLoggedInUser(session));
			return "add-book";
		}

		bookService.createBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		model.addAttribute("user", getLoggedInUser(session));
		return "redirect:/books";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model, HttpSession session) {
		final Book book = bookService.findBookById(id);

		model.addAttribute("book", book);
		model.addAttribute("user", getLoggedInUser(session));
		return "update-book";
	}

	@RequestMapping("/update-book/{id}")
	public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			book.setId(id);
			model.addAttribute("user", getLoggedInUser(session));
			return "update-book";
		}

		bookService.updateBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		model.addAttribute("user", getLoggedInUser(session));
		return "redirect:/books";
	}

	@RequestMapping("/remove-book/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		bookService.deleteBook(id);

		model.addAttribute("book", bookService.findAllBooks());
		model.addAttribute("user", getLoggedInUser(session));
		return "redirect:/books";
	}

}
