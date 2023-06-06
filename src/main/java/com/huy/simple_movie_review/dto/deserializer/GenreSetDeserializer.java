package com.huy.simple_movie_review.dto.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huy.simple_movie_review.entities.Genre;
import com.huy.simple_movie_review.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GenreSetDeserializer extends JsonDeserializer<Set<Genre>> {
    private final GenreRepository genreRepo;

    public GenreSetDeserializer(GenreRepository genreRepo){
        this.genreRepo = genreRepo;
    }

    @Override
    public Set<Genre> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = mapper.readTree(jsonParser);

        Set<Genre> genres = new HashSet<>();
        if(node.isArray()){
            for(JsonNode genreNode : node){
                Long id = genreNode.asLong();
                Genre genre = genreRepo.findById(id).orElse(null);
                genres.add(genre);
            }
        }
        return genres;
    }
}
