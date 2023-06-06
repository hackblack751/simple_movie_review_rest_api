package com.huy.simple_movie_review.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank(message = "username is mandatory")
    private String username;

    @NotBlank(message = "email is mandatory")
    @Email(message = "wrong email format")
    private String email;
    private ProfileDto profile;

}
