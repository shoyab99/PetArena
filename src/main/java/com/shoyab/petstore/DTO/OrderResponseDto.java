package com.shoyab.petstore.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderResponseDto {
    private int orderId;

    private double totalAmount;

    private String status;

    private String message;
    private String orderStatus;
    private String paymentStatus;
}
