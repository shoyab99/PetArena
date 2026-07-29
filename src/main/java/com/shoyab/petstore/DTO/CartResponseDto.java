package com.shoyab.petstore.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartResponseDto {
		private int cartItemId;
		private int productId;
		private String productName;
		private String description;
		private String imageUrl;
		private double price;
		private double discountPrice;
		private String categoryName;
		private int quantity;
		
		
}
