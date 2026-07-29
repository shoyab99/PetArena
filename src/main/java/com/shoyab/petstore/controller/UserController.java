package com.shoyab.petstore.controller;



import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoyab.petstore.DTO.LoginRequest;
import com.shoyab.petstore.DTO.UserRequestDto;
import com.shoyab.petstore.DTO.UserResponseDto;
import com.shoyab.petstore.model.Users;
import com.shoyab.petstore.service.UserService;
import jakarta.validation.Valid;

//import jakarta.websocket.server.PathParam;
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
	private UserService service;
	
	public UserController(UserService service) {
		
		this.service = service;
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest login){
		return service.login(login);
	}
	
@GetMapping
	public List<Users> getall(){
		return service.getallu();
	}
@GetMapping("/id/{id}")
public Users getbyid(@PathVariable int id) {
	return service.findbyid(id);
}
@PostMapping
	public UserResponseDto adduser(@Valid @RequestBody UserRequestDto user) {
	return service.addusers(user);
}
@PutMapping
public Users updateusers(@RequestBody Users user) {
	return service.updateall(user);
}
@DeleteMapping("/id/{id}")
public void deleteuser(@PathVariable int id) {
	service.deleteuser(id);
	
}
@PutMapping("/id/{id}")
public UserResponseDto updateUser(@PathVariable int id,
                        @RequestBody Users user) {

    return service.updateUser(id, user);

}
}
