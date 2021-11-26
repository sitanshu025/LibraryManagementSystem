package com.bca.librarymanagementsystem.service;

import java.util.List;

import com.bca.librarymanagementsystem.entity.Category;

public interface CategoryService {

	List<Category> findAllCategories();

	Category findCategoryById(Long id);

	void createCategory(Category category);

	void updateCategory(Category category);

	void deleteCategory(Long id);

}
