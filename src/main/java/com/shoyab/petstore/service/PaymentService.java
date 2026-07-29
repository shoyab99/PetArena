package com.shoyab.petstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoyab.petstore.model.Payment;
import com.shoyab.petstore.repository.PaymentRepo;

@Service
public class PaymentService {
	private PaymentRepo repo;

	public PaymentService(PaymentRepo repo) {
		super();
		this.repo = repo;
	}

	public Payment save(Payment payment) {
		// TODO Auto-generated method stub
		return repo.save(payment);
	}

	public List<Payment> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Payment getById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}
	

}
