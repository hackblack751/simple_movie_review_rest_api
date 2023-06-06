package com.huy.simple_movie_review.dto.response;

import lombok.Data;

@Data
public class ErrorMessage {
    private Integer status;
    private String message;
}
