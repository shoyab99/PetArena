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

import com.shoyab.petstore.model.CartItem;
import com.shoyab.petstore.service.CartItemSerice;
@CrossOrigin
@RestController
@RequestMapping("/items")
public class CartItemController {
private CartItemSerice service;

public CartItemController(CartItemSerice service) {

	this.service = service;
}
@GetMapping
public List<CartItem> getall(){
	return service.getall();
}
@GetMapping("/{id}")
public CartItem getid(@PathVariable int id) {
	return service.getbyid(id);
}
@PostMapping
public CartItem additem(@RequestBody CartItem item) {
	return service.additem(item);
}
@DeleteMapping("/{id}")
public void deleteid(@PathVariable int id) {
	service.deleteid(id);
}
@DeleteMapping("/id/all")
public void deleteall() {
	service.deleteall();
}
}
