package com.shoyab.petstore.controller;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoyab.petstore.DTO.DashboardDto;
import com.shoyab.petstore.service.Adminservice;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private Adminservice service;

    public AdminController(Adminservice service) {
        this.service = service;
    }

    @GetMapping("/dashboard")
    public DashboardDto dashboard() {
        return service.getDashboard();
    }

}