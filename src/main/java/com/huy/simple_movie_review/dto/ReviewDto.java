package com.huy.simple_movie_review.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReviewDto {

    private Long userId;
    private Long movieId;
    @NotBlank(message = "header must not be empty")
    private String header;
    @NotBlank(message = "review must not be empty")
    private String text;
    private Boolean isSuspended;

    @DecimalMax("9.9")
    @DecimalMin("0.1")
    private Double rating;
}
