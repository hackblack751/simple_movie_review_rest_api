package com.huy.simple_movie_review.services;

import com.huy.simple_movie_review.dto.MovieDto;
import com.huy.simple_movie_review.entities.Genre;
import com.huy.simple_movie_review.entities.Movie;
import com.huy.simple_movie_review.exception.DataNotFoundException;
import com.huy.simple_movie_review.repositories.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    private final MovieRepository movieRepo;

    @Autowired
    public MovieService(MovieRepository movieRepo){
        this.movieRepo = movieRepo;
    }

    @Transactional
    public List<Movie> getAllMovie(){
        return movieRepo.findAll();
    }

    @Transactional
    public Movie getMovieById(Long movieId){
        return movieRepo.findById(movieId).orElse(null);
    }

    @Transactional
    public Movie createMovie(MovieDto movieDto){
        String encodedImage = movieDto.getImage();
        byte[] decodedImage = Base64.getDecoder().decode(encodedImage);
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setImage(decodedImage);
        movie.setLength(movieDto.getLength());
        movie.setRating(movieDto.getRating());
        movie.setGenres(movieDto.getGenres());
        movie.setReleasedDate(movieDto.getReleasedDate());

        movie = movieRepo.save(movie);
        return movie;
    }

    @Transactional
    public Movie addGenre(MovieDto movieDto){
        if(!movieRepo.existsById(movieDto.getMovieId())){
            throw new DataNotFoundException("Movie with id " + movieDto.getMovieId() + " not exists");
        }

        Movie movie = movieRepo.findById(movieDto.getMovieId()).orElse(null);
        Set<Genre> genres = movieDto.getGenres();
        for(Genre genre : genres){
            movie.addGenre(genre);
        }

        movie = movieRepo.save(movie);

        return movie;
    }

    @Transactional
    public Movie removeGenre(MovieDto movieDto){
        if(!movieRepo.existsById(movieDto.getMovieId())){
            throw new DataNotFoundException("Movie with id " + movieDto.getMovieId() + " not exist");
        }
        Movie movie = movieRepo.findById(movieDto.getMovieId()).orElse(null);
        Set<Genre> genres = movieDto.getGenres();
        for(Genre genre : genres){
            movie.removeGenre(genre);
        }

        movie = movieRepo.save(movie);

        return movie;
    }

    @Transactional
    public void removeMovie(Long id){
        if(!movieRepo.existsById(id)) {
            throw new DataNotFoundException("Movie with id " + id + " not exist");
        }

        Movie movie = movieRepo.findById(id).orElse(null);
        movieRepo.delete(movie);
    }


    @Transactional
    public Movie updateMovie(MovieDto movieDto){
        if(!movieRepo.existsById(movieDto.getMovieId())){
            throw new DataNotFoundException("Movie with id " + movieDto.getMovieId() + " not exist");
        }

        Movie movie = movieRepo.findById(movieDto.getMovieId()).orElse(null);
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setLength(movieDto.getLength());
        movie.setReleasedDate(movieDto.getReleasedDate());
        movie.setImage(Base64.getDecoder().decode(movieDto.getImage()));

        movie = movieRepo.save(movie);

        return movie;
    }

}
