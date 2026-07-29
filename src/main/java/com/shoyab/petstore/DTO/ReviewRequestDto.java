package com.shoyab.petstore.DTO;

import lombok.Data;

@Data
public class ReviewRequestDto {
    private int userId;

    private int productId;

    private int rating;

    private String comment;
}
