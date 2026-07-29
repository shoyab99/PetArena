package com.shoyab.petstore.DTO;

import lombok.Data;

@Data
public class ProductRequestDto {

    private String productName;
    private String description;
    private double price;
    private Double discountPrice;
    private int stock;
    private String brand;
    private String imageUrl;
    private int categoryId;
    private String badge;
}