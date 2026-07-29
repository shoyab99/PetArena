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

import com.shoyab.petstore.DTO.OrderDetailsDto;
import com.shoyab.petstore.DTO.OrderRequestDto;
import com.shoyab.petstore.DTO.OrderResponseDto;
import com.shoyab.petstore.model.Orders;
import com.shoyab.petstore.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {
	private OrderService service;

	public OrderController(OrderService service) {
		super();
		this.service = service;
	}
	@GetMapping
	public List<Orders> getall(){
		return service.getall();
	}
	@GetMapping("/{id}")
	public OrderDetailsDto getOrder(@PathVariable int id) {
	    return service.getOrderDetails(id);
	}
	@PostMapping
	public OrderResponseDto placeOrder(@RequestBody OrderRequestDto dto) {
	    return service.placeOrder(dto);
	}
	@DeleteMapping("/{id}")
	public void deleteid(@PathVariable int id) {
		service.deleteid(id);
	}
	   // NEW - Get all orders of a particular user
    @GetMapping("/user/{userId}")
    public List<OrderResponseDto> getOrdersByUser(@PathVariable int userId) {
        return service.getOrdersByUser(userId);
    }

    // NEW - Cancel Order
    @PostMapping("/{orderId}/cancel")
    public String cancelOrder(@PathVariable int orderId) {
        service.cancelOrder(orderId);
        return "Order Cancelled Successfully";
    }
    

}
