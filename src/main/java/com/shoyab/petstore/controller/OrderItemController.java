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

import com.shoyab.petstore.model.OrderItem;
import com.shoyab.petstore.model.Orders;
import com.shoyab.petstore.service.OrderItemService;
@CrossOrigin
@RestController
@RequestMapping("/order-items")
public class OrderItemController {
private OrderItemService service;

public OrderItemController(OrderItemService service) {
	super();
	this.service = service;
}
@GetMapping
public List<OrderItem> getall(){
	return service.getall();
}
@GetMapping("/{id}")
public OrderItem getid(@PathVariable int id) {
	return service.getid(id);
}
@PostMapping
public OrderItem addorder(@RequestBody OrderItem ord) {
	return service.addord(ord);
}
@DeleteMapping("/{id}")
public void deleteid(@PathVariable int id) {
	service.deleteid(id);
}
@DeleteMapping("/all")
public void deleteall() {
	service.deleteall();
}

}
