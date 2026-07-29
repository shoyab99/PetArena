package com.shoyab.petstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.shoyab.petstore.DTO.AdminUserDto;
import com.shoyab.petstore.service.UserService;

@RestController
@RequestMapping("/admin/users")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminUserController {

    private final UserService service;

    public AdminUserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<AdminUserDto> getAllUsers() {

        return service.getAllUsersForAdmin();

    }

}