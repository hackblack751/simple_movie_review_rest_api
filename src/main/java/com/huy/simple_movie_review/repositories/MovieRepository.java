package com.huy.simple_movie_review.repositories;

import com.huy.simple_movie_review.dto.MovieDto;
import com.huy.simple_movie_review.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("UPDATE Movie m SET m.rating =:raing WHERE m.id =:movieId")
    void updateMovieRating(Long movieId, Double rating);
}
