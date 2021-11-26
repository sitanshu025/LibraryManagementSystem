package com.bca.librarymanagementsystem;

import com.bca.librarymanagementsystem.entity.Publisher;
import com.bca.librarymanagementsystem.entity.User;
import com.bca.librarymanagementsystem.entity.UserType;
import com.bca.librarymanagementsystem.entity.Author;
import com.bca.librarymanagementsystem.entity.Book;
import com.bca.librarymanagementsystem.entity.Category;
import com.bca.librarymanagementsystem.service.BookService;
import com.bca.librarymanagementsystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LibrarymanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarymanagementsystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate(BookService bookService, UserService userService) {
		return (args) -> {

			Book book = new Book("Test isbn", "Test name", "Test serial name", "Test description");
			Author author = new Author("Test author name", "Test description");
			Category category = new Category("Test category name");
			Publisher publisher = new Publisher("Test publisher name");

			book.addAuthors(author);
			book.addCategories(category);
			book.addPublishers(publisher);

			bookService.createBook(book);

			Book book1 = new Book("Test isbn1", "Test name1", "Test serial name1", "Test description1");
			Author author1 = new Author("Test author name1", "Test description1");
			Category category1 = new Category("Test category name1");
			Publisher publisher1 = new Publisher("Test publisher name1");

			book1.addAuthors(author1);
			book1.addCategories(category1);
			book1.addPublishers(publisher1);

			bookService.createBook(book1);

			Book book2 = new Book("Test isbn2", "Test name2", "Test serial name2", "Test description2");
			Author author2 = new Author("Test author name2", "Test description2");
			Category category2 = new Category("Test category name2");
			Publisher publisher2 = new Publisher("Test publisher name2");

			book2.addAuthors(author2);
			book2.addCategories(category2);
			book2.addPublishers(publisher2);

			bookService.createBook(book2);

			User adminUser = User.builder()
					.email("admin@library.com")
					.password("admin")
					.name("Admin")
					.address("Library")
					.type(UserType.ADMIN)
					.build();
			userService.createUser(adminUser);

			User user = User.builder()
					.email("user@library.com")
					.password("user")
					.name("User")
					.address("Library")
					.type(UserType.USER)
					.build();
			userService.createUser(user);

		};
	}
}
