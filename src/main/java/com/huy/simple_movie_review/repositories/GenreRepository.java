package com.huy.simple_movie_review.repositories;

import com.huy.simple_movie_review.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByNameLike(String name);
}
