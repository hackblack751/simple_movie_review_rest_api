package com.huy.simple_movie_review.repositories;

import com.huy.simple_movie_review.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
