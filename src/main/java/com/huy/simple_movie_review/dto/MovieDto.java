package com.huy.simple_movie_review.dto;



import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.huy.simple_movie_review.dto.deserializer.GenreSetDeserializer;
import com.huy.simple_movie_review.entities.Genre;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class MovieDto {
    private Long movieId;
    private String title;
    private String description;
    private LocalDate releasedDate;
    private Double rating;
    private Integer length;

    private String image;
    @JsonDeserialize(using = GenreSetDeserializer.class)
    private Set<Genre> genres;



}
