package com.huy.simple_movie_review.repositories;

import com.huy.simple_movie_review.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(r.rating) FROM Review r JOIN r.movies m WHERE m.id =:movieId")
    Double getAverageRatingByMovieId(Long movieId);
}
