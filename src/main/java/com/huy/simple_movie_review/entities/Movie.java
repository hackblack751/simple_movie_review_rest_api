package com.huy.simple_movie_review.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "releasedDate", columnDefinition = "DATE")
    private LocalDate releasedDate;
    @Column(name = "rating", nullable = false, columnDefinition = "DECIMAL(3,1) DEFAULT 0.0")
    private Double rating;

    @Column(name = "length", columnDefinition = "INT DEFAULT 0.0")
    private Integer length;

    @Column(name = "createdAt", nullable = false, columnDefinition = "TIMESTAMP", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "MovieGenre",
                joinColumns = {@JoinColumn(name = "movieId",
                                    foreignKey = @ForeignKey(name = "fk_MovieGenre_Movies_movieId"))},
                inverseJoinColumns = {@JoinColumn(name = "genreId",
                                    foreignKey = @ForeignKey(name = "fk_MovieGenre_Genres_genreId"))}
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "MoiveReview",
            joinColumns = {@JoinColumn(name = "movieId",
                    foreignKey = @ForeignKey(name = "fk_MovieReview_Movies_movieId"))},
            inverseJoinColumns = {@JoinColumn(name = "reviewId",
                    foreignKey = @ForeignKey(name = "fk_MovieReview_Reviews_reviewId"))}
    )
    private Set<Review> reviews = new HashSet<>();



    public Movie(){

    }

    public Movie(String title, String description, LocalDate releasedDate, Double rating, Integer length, byte[] image) {
        this.title = title;
        this.description = description;
        this.releasedDate = releasedDate;
        this.rating = rating;
        this.length = length;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }


    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public void addGenre(Genre genre){
        if(genre != null){
            Genre existedGenre = genres.stream()
                            .filter(g -> g.getName().equals(genre.getName()))
                            .findFirst().orElse(null);
            if(existedGenre == null){
                genres.add(genre);
                genre.getMovies().add(this);
            }
        }
    }

    public void removeGenre(Genre genre){
        if(genre != null){
            Genre existedGenre = genres.stream()
                    .filter(g -> g.getName().equals(genre.getName()))
                    .findFirst().orElse(null);
            if(existedGenre != null){
                genres.remove(genre);
                genre.getMovies().remove(this);
            }
        }
    }
}
