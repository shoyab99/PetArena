package com.shoyab.petstore.DTO;

import com.shoyab.petstore.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;

	private Role role;
	private boolean verified;
}

