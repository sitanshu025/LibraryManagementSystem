package com.bca.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bca.librarymanagementsystem.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
