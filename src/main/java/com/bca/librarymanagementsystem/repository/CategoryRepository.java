package com.bca.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bca.librarymanagementsystem.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
