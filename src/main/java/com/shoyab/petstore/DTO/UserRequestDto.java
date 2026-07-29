package com.shoyab.petstore.DTO;

import com.shoyab.petstore.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private Role role;
}
