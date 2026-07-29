package com.shoyab.petstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoyab.petstore.DTO.AdminOrderDto;
import com.shoyab.petstore.DTO.UpdateOrderStatusDto;
import com.shoyab.petstore.service.OrderService;

@RestController
@RequestMapping("/admin/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminorderController {
	private OrderService service;
	
	public AdminorderController(OrderService service) {
		super();
		this.service = service;
	}
@GetMapping
	public List<AdminOrderDto> getallorders(){
		return service.getAllOrdersForAdmin();
	}
@PutMapping("/{id}/status")
public void updateStatus(
        @PathVariable int id,
        @RequestBody UpdateOrderStatusDto dto) {

    service.updateOrderStatus(id, dto.getStatus());
}
}
