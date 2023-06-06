package com.huy.simple_movie_review.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 100, nullable = false, unique = true)
    private String username;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "createdAt", updatable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;



    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "profileId",
            foreignKey = @ForeignKey(name = "fk_Users_Profiles_profileId"),
            referencedColumnName = "id")
    private Profile profile;


    @ManyToMany()
    @JoinTable(name = "UserReview",
                joinColumns = {@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "UserReview_Users_userId"))},
                inverseJoinColumns = {@JoinColumn(name = "reviewId", foreignKey = @ForeignKey(name = "UserReview_Reviews_reviewId"))}
    )
    private Set<Review> reviews = new HashSet<>();


    public User(){

    }

    public User(String username, String email, LocalDateTime createdAt, Profile profile) {
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}
