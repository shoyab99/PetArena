package com.shoyab.petstore.DTO;

import lombok.Data;

@Data
public class ProductResponseDto {

    private int productId;
    private int categoryId;
    private String productName;
    private String description;
    private double price;
    private Double discountPrice;
    private int stock;
    private String brand;
    private String imageUrl;
    private String badge;
    private String categoryName;
}