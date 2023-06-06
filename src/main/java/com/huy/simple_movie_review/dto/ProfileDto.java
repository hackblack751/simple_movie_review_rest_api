package com.huy.simple_movie_review.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.huy.simple_movie_review.dto.deserializer.GenderDeserializer;
import com.huy.simple_movie_review.entities.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;

    @JsonDeserialize(using = GenderDeserializer.class)
    private Gender gender;

}
