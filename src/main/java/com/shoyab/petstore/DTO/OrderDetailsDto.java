package com.shoyab.petstore.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.shoyab.petstore.model.Address;
import com.shoyab.petstore.model.OrderItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsDto {
    private int orderId;
    private double totalAmount;
    private String orderStatus;
    private String paymentStatus;
    private LocalDateTime orderDate;

    private Address address;

    private List<OrderItem> orderitems;
}
