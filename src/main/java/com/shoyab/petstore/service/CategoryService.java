package com.shoyab.petstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoyab.petstore.model.Category;
import com.shoyab.petstore.repository.CategoryRepo;

@Service
public class CategoryService {
	private CategoryRepo repo;

	public CategoryService(CategoryRepo repo) {
	
		this.repo = repo;
	}

	public List<Category> getall1() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Category addcat(Category cat) {
		// TODO Auto-generated method stub
		return repo.save(cat);
	}

	public Category getbyid(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	public void deletebyid(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}
	
	
}
