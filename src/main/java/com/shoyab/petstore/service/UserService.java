package com.shoyab.petstore.service;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shoyab.petstore.DTO.AdminUserDto;
import com.shoyab.petstore.DTO.LoginRequest;
import com.shoyab.petstore.DTO.UserRequestDto;
import com.shoyab.petstore.DTO.UserResponseDto;
import com.shoyab.petstore.Exception.ResourceNotFoundException;
import com.shoyab.petstore.model.Role;
import com.shoyab.petstore.model.Users;
import com.shoyab.petstore.repository.UserRepo;

@Service
public class UserService {
private UserRepo repo;


public UserService(UserRepo repo) {
	
	this.repo = repo;
}

public List<Users> getallu() {
	return repo.findAll();
	}
public List<AdminUserDto> getAllUsersForAdmin() {

    return repo.findAll()
            .stream()
            .map(user -> {

                AdminUserDto dto = new AdminUserDto();

                dto.setUserId(user.getUserId());
                dto.setFirstName(user.getFirstName());
                dto.setLastName(user.getLastName());
                dto.setEmail(user.getEmail());
                dto.setPhoneNumber(user.getPhoneNumber());
                dto.setRole(user.getRole().name());
                dto.setVerified(user.isVerified());
                dto.setCreatedAt(user.getCreatedAt());

                return dto;

            }).toList();

}
public Users findbyid(int id) {
	return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found"+id));
}
public UserResponseDto addusers(UserRequestDto dto) {

    Users user = new Users();

    user.setFirstName(dto.getFirstName());
    user.setLastName(dto.getLastName());
    user.setEmail(dto.getEmail());
    user.setPhoneNumber(dto.getPhoneNumber());
    user.setPassword(dto.getPassword());
    user.setRole(Role.USER);

    user.setVerified(false);
    user.setCreatedAt(LocalDateTime.now());
    user.setUpdatedAt(LocalDateTime.now());

    Users savedUser = repo.save(user);

    UserResponseDto response = new UserResponseDto();

    response.setUserId(savedUser.getUserId());
    response.setFirstName(savedUser.getFirstName());
    response.setLastName(savedUser.getLastName());
    response.setEmail(savedUser.getEmail());
    response.setPhoneNumber(savedUser.getPhoneNumber());
    response.setRole(savedUser.getRole());
    response.setVerified(savedUser.isVerified());

    return response;
}
public Users updateall(Users user) {
	return repo.save(user);
}
	public void deleteuser(int id) {
		 repo.deleteById(id);
	}

	public ResponseEntity<?> login(LoginRequest request) {

	    Users user = repo.findByEmailOrPhoneNumber(
	            request.getUsername(),
	            request.getUsername()
	    ).orElse(null);

	    if (user == null) {
	        return ResponseEntity.badRequest().body("User not found");
	    }

	    if (!user.getPassword().equals(request.getPassword())) {
	        return ResponseEntity.badRequest().body("Invalid Password");
	    }

	    UserResponseDto response = new UserResponseDto();

	    response.setUserId(user.getUserId());
	    response.setFirstName(user.getFirstName());
	    response.setLastName(user.getLastName());
	    response.setEmail(user.getEmail());
	    response.setPhoneNumber(user.getPhoneNumber());
	    response.setRole(user.getRole());
	    response.setVerified(user.isVerified());

	    return ResponseEntity.ok(response);
	}

	public UserResponseDto updateUser(int id, Users updatedUser) {

	    Users user = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("User Not Found"));

	    user.setFirstName(updatedUser.getFirstName());
	    user.setLastName(updatedUser.getLastName());
	    user.setPhoneNumber(updatedUser.getPhoneNumber());

	    Users savedUser = repo.save(user);

	    UserResponseDto dto = new UserResponseDto();

	    dto.setUserId(savedUser.getUserId());
	    dto.setFirstName(savedUser.getFirstName());
	    dto.setLastName(savedUser.getLastName());
	    dto.setEmail(savedUser.getEmail());
	    dto.setPhoneNumber(savedUser.getPhoneNumber());
	    dto.setRole(savedUser.getRole());
	    dto.setVerified(savedUser.isVerified());

	    return dto;
	}
	
	


	
	
}
