package com.shoyab.petstore.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewResponseDto {
    private int reviewId;

    private String userName;

    private int rating;

    private String comment;

    private LocalDateTime reviewDate;
}
