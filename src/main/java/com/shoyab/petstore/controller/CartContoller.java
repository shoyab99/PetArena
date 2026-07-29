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

import com.shoyab.petstore.DTO.CartRequestDto;
import com.shoyab.petstore.DTO.CartResponseDto;
import com.shoyab.petstore.model.Cart;
import com.shoyab.petstore.service.CartService;
@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartContoller {
	private CartService service;

	public CartContoller(CartService service) {
		super();
		this.service = service;
	}
//	@GetMapping
//	public List<Cart> getall(){
//		return service.getallcart();
//	}
	@PutMapping("/{cartItemId}")
	public CartResponseDto updateQuantity(@PathVariable int cartItemId, @RequestBody CartRequestDto dto) {
	    return service.updateQuantity(cartItemId, dto.getQuantity());
	}
	@PostMapping
	public CartResponseDto addcart(@RequestBody  CartRequestDto dto) {
		return service.addcarts(dto);
	}
	@GetMapping("/{userId}")
	public List<CartResponseDto> getid(@PathVariable int userId) {
		return service.findid(userId);
		
	}
	@DeleteMapping("/{cartItemId}")
	public void deletecart(@PathVariable int cartItemId) {
		service.deletecarts(cartItemId);
	}

}
