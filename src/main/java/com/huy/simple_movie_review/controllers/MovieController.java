package com.huy.simple_movie_review.controllers;

import com.huy.simple_movie_review.dto.MovieDto;
import com.huy.simple_movie_review.entities.Movie;
import com.huy.simple_movie_review.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAllMovie(){
        List<Movie> movieList = movieService.getAllMovie();
        return ResponseEntity.ok(movieList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getMovieById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createMovie(@RequestBody MovieDto movieDto){
        Movie movie = movieService.createMovie(movieDto);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/add_genre")
    public ResponseEntity<Object> addGenre(@RequestBody MovieDto movieDto){
        Movie movie = movieService.addGenre(movieDto);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/remove_genre")
    public ResponseEntity<Object> removeGenre(@RequestBody MovieDto movieDto){
        Movie movie = movieService.removeGenre(movieDto);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeMovie(@PathVariable(name = "id") Long movieId){
        movieService.removeMovie(movieId);
        return ResponseEntity.ok("Remove movie with id " + movieId);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateMovie(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.updateMovie(movieDto));
    }


}
