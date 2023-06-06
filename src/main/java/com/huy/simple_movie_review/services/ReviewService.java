package com.huy.simple_movie_review.services;

import com.huy.simple_movie_review.dto.ReviewDto;
import com.huy.simple_movie_review.entities.Movie;
import com.huy.simple_movie_review.entities.Review;
import com.huy.simple_movie_review.entities.User;
import com.huy.simple_movie_review.exception.DataNotFoundException;
import com.huy.simple_movie_review.repositories.MovieRepository;
import com.huy.simple_movie_review.repositories.ReviewRepository;
import com.huy.simple_movie_review.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepo;
    private final MovieRepository movieRepo;
    private final UserRepository userRepo;

    @Autowired
    public ReviewService(ReviewRepository reviewRepo, MovieRepository movieRepo, UserRepository userRepo){
        this.reviewRepo = reviewRepo;
        this.movieRepo = movieRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Review createReview(ReviewDto reviewDto){
        Movie movie = movieRepo.findById(reviewDto.getMovieId()).orElse(null);
        User user = userRepo.findById(reviewDto.getUserId()).orElse(null);
        if(movie == null ) {
            throw new DataNotFoundException("Movie with id " + reviewDto.getMovieId() + " not exist");
        }
        if(user == null){
            throw new DataNotFoundException("User with id " + reviewDto.getUserId() + " not exist");
        }

        Review review = new Review();
        review.setHeader(reviewDto.getHeader());
        review.setRating(reviewDto.getRating());
        review.setSuspended(false);
        review.setText(reviewDto.getText());
        review.addMovie(movie);
        review.addUser(user);

        review = reviewRepo.save(review);

        Double avgRating = reviewRepo.getAverageRatingByMovieId(movie.getId());
        movieRepo.updateMovieRating(movie.getId(), avgRating);

        return review;
    }

    @Transactional
    public Review getReviewById(Long id){
        return reviewRepo.findById(id).orElse(null);
    }


    @Transactional
    public Review removeById(Long reviewId){
        Review review = reviewRepo.findById(reviewId).orElse(null);
        if(review == null){
            throw new DataNotFoundException("Review with id " + reviewId + " not exist");
        }
        reviewRepo.delete(review);
        return review;
    }
}
