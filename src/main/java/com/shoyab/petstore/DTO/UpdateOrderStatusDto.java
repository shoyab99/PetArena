package com.shoyab.petstore.DTO;

import com.shoyab.petstore.model.OrderStatus;

import lombok.Data;
@Data
public class UpdateOrderStatusDto {
	private OrderStatus status;
}
