package com.shoyab.petstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoyab.petstore.model.Category;
import com.shoyab.petstore.service.CategoryService;
@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
	private CategoryService service;

	public CategoryController(CategoryService servie) {
	
		this.service = servie;
	}
	@GetMapping
	public List<Category> getall(){
		return service.getall1();
	}
	@PostMapping
	public Category addcategory(@RequestBody Category cat) {
		return service.addcat(cat);
	}
	@GetMapping("/{id}")
	public Category getid(@PathVariable int id) {
		return service.getbyid(id);
	}
	@DeleteMapping("/{id}")
	public  void deletecat(@PathVariable int id) {
		service.deletebyid(id);
	}
	
	
}
