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

import com.shoyab.petstore.model.Payment;
import com.shoyab.petstore.service.PaymentService;
@CrossOrigin
@RestController
@RequestMapping("/payment")
public class PaymentController {
	private PaymentService service;

	public PaymentController(PaymentService service) {
		super();
		this.service = service;
	}
	@PostMapping
    public Payment save(@RequestBody Payment payment) {
        return service.save(payment);
    }

    @GetMapping
    public List<Payment> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable int id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

}
