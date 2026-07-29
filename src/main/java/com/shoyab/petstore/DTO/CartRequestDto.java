package com.shoyab.petstore.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartRequestDto {
	private int userId;
	private int productId;
	private int quantity;

}
