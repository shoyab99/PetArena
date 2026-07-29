package com.shoyab.petstore.DTO;

import com.shoyab.petstore.model.PaymentMethod;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequestDto {
  private int userId;
  private int addressId;
  private PaymentMethod paymentMethod;
}
