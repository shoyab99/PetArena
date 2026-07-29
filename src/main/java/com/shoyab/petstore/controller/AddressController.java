package com.shoyab.petstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoyab.petstore.model.Address;
import com.shoyab.petstore.service.AddressService;

import jakarta.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/address")
public class AddressController {
	private AddressService service;

	public AddressController(AddressService service) {
		super();
		this.service = service;
	}
	@GetMapping
	public List<Address> show(){
		return service.showadress();
		}
	@GetMapping("/{id}")
	public Address getid(@PathVariable int id) {
		return service.findbyid1(id);
	}
	@PostMapping
	public Address addAdd(@Valid @RequestBody Address add) {
		return service.addAddress(add);
	}
	@PutMapping
	public Address updateadd(@RequestBody Address add) {
		return service.updateAddress(add);
	}
	@DeleteMapping("/{id}")
	public void deleteadd(@PathVariable int id) {
		service.deleteadd(id);
	}

}
