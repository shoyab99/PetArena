package com.shoyab.petstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoyab.petstore.model.OrderItem;
import com.shoyab.petstore.repository.OrderItemRepo;

@Service
public class OrderItemService {
	private OrderItemRepo repo;

	public OrderItemService(OrderItemRepo repo) {
		super();
		this.repo = repo;
	}

	public List<OrderItem> getall() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public OrderItem getid(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	public OrderItem addord(OrderItem ord) {
		// TODO Auto-generated method stub
		return repo.save(ord);
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
