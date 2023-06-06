package com.huy.simple_movie_review.services;

import com.huy.simple_movie_review.dto.GenreDto;
import com.huy.simple_movie_review.entities.Genre;
import com.huy.simple_movie_review.exception.DataNotFoundException;
import com.huy.simple_movie_review.repositories.GenreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepo;

    @Autowired
    public GenreService(GenreRepository genreRepo){
        this.genreRepo = genreRepo;
    }

    @Transactional
    public List<Genre> getAllGenre(){
        return genreRepo.findAll();
    }

    @Transactional
    public Genre createGenre(GenreDto genreDto){
        if(genreDto == null && genreDto.getName() == null && genreDto.getName().isBlank()){
            throw new DataNotFoundException("Empty genre name");
        }
        genreDto.setName(genreDto.getName().toLowerCase());
        Genre existedGenre = genreRepo.findByNameLike(genreDto.getName()).orElse(null);

        if(existedGenre != null){
            throw new RuntimeException("Genre already exist");
        }

        Genre genre = new Genre();
        genre.setName(genreDto.getName());
        genre = genreRepo.save(genre);

        return genre;
    }

    @Transactional
    public void removeGenre(Long genreId){
        if(!genreRepo.existsById(genreId)) {
            throw new DataNotFoundException("Genre with id " + genreId + " not exist");
        }
        Genre genre = genreRepo.findById(genreId).orElse(null);
        genreRepo.delete(genre);
    }



}
