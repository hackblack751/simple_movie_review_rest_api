package com.huy.simple_movie_review.controllers;

import com.huy.simple_movie_review.dto.GenreDto;
import com.huy.simple_movie_review.entities.Genre;
import com.huy.simple_movie_review.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllGenres(){
        List<Genre> genreList = genreService.getAllGenre();
        return ResponseEntity.ok(genreList);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createGenre(@RequestBody GenreDto genreDto){
        Genre genre = genreService.createGenre(genreDto);
        return ResponseEntity.ok(genre);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeGenre(@PathVariable(name = "id") Long id){
        genreService.removeGenre(id);
        return ResponseEntity.ok("Remove genre");
    }
}
