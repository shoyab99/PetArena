package com.shoyab.petstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoyab.petstore.model.CartItem;
import com.shoyab.petstore.repository.CartItemRepo;

@Service
public class CartItemSerice {
	private CartItemRepo repo;

	public CartItemSerice(CartItemRepo repo) {
	
		this.repo = repo;
	}

	public List<CartItem> getall() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public CartItem getbyid(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	public CartItem additem(CartItem item) {
		// TODO Auto-generated method stub
		return repo.save(item);
	}

	public void deleteid(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	public void deleteall() {
		// TODO Auto-generated method stub
		repo.deleteAll();
		
	}
	

}
