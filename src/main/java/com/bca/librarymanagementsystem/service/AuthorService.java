package com.bca.librarymanagementsystem.service;

import java.util.List;

import com.bca.librarymanagementsystem.entity.Author;

public interface AuthorService {

	List<Author> findAllAuthors();

	Author findAuthorById(Long id);

	void createAuthor(Author author);

	void updateAuthor(Author author);

	void deleteAuthor(Long id);

}
