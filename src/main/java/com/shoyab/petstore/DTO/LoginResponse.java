package com.shoyab.petstore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	   private int userId;
	    private String firstName;
	    private String email;
	    private String role;
}
