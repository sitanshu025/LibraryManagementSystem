package com.bca.librarymanagementsystem.service;

import com.bca.librarymanagementsystem.entity.Book;

import java.util.List;

public interface BookService {

	List<Book> findAllBooks();
	
	List<Book> searchBooks(String keyword);

	List<Book> findBookByUser(String email);

	Book findBookById(Long id);

	void createBook(Book book);

	void updateBook(Book book);

	void deleteBook(Long id);

}
