package com.shoyab.petstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoyab.petstore.model.Address;
import com.shoyab.petstore.repository.AddressRepo;

@Service
public class AddressService {
	private AddressRepo repo;

	public AddressService(AddressRepo repo) {
	
		this.repo = repo;
	}
	public List<Address> showadress(){
		return repo.findAll();
	}
	public Address findbyid1(int id) {
		return repo.findById(id).orElse(null);
	}
	public Address addAddress(Address add) {
		return repo.save(add);
	}
	public Address updateAddress(Address add) {
		return repo.save(add);
	}
	public void deleteadd(int id) {
		repo.deleteById(id);
	}
}
