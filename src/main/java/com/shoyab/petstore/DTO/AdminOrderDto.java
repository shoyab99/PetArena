package com.shoyab.petstore.DTO;

import java.time.LocalDateTime;

import com.shoyab.petstore.model.OrderStatus;
import com.shoyab.petstore.model.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderDto {

    private int orderId;

    private String customer;

    private double totalAmount;

    private PaymentStatus paymentStatus;

    private OrderStatus status;

    private LocalDateTime orderDate;

}