package com.bca.librarymanagementsystem.controller;

import com.bca.librarymanagementsystem.entity.Category;
import com.bca.librarymanagementsystem.service.CategoryService;
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
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping("/categories")
	public String findAllCategories(Model model, HttpSession session) {
		final List<Category> categories = categoryService.findAllCategories();

		model.addAttribute("categories", categories);
		model.addAttribute("user", getLoggedInUser(session));
		return "list-categories";
	}

	@RequestMapping("/category/{id}")
	public String findCategoryById(@PathVariable("id") Long id, Model model, HttpSession session) {
		final Category category = categoryService.findCategoryById(id);

		model.addAttribute("category", category);
		model.addAttribute("user", getLoggedInUser(session));
		return "list-category";
	}

	@GetMapping("/addCategory")
	public String showCreateForm(Category category, Model model, HttpSession session) {
		model.addAttribute("user", getLoggedInUser(session));
		return "add-category";
	}

	@RequestMapping("/add-category")
	public String createCategory(Category category, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("user", getLoggedInUser(session));
			return "add-category";
		}

		categoryService.createCategory(category);
		model.addAttribute("category", categoryService.findAllCategories());
		model.addAttribute("user", getLoggedInUser(session));
		return "redirect:/categories";
	}

	@GetMapping("/updateCategory/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model, HttpSession session) {
		final Category category = categoryService.findCategoryById(id);

		model.addAttribute("category", category);
		model.addAttribute("user", getLoggedInUser(session));
		return "update-category";
	}

	@RequestMapping("/update-category/{id}")
	public String updateCategory(@PathVariable("id") Long id, Category category, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			category.setId(id);
			model.addAttribute("user", getLoggedInUser(session));
			return "update-category";
		}

		categoryService.updateCategory(category);
		model.addAttribute("category", categoryService.findAllCategories());
		model.addAttribute("user", getLoggedInUser(session));
		return "redirect:/categories";
	}

	@RequestMapping("/remove-category/{id}")
	public String deleteCategory(@PathVariable("id") Long id, Model model, HttpSession session) {
		categoryService.deleteCategory(id);

		model.addAttribute("category", categoryService.findAllCategories());
		model.addAttribute("user", getLoggedInUser(session));
		return "redirect:/categories";
	}

}
