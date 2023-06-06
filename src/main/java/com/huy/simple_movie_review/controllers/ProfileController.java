package com.huy.simple_movie_review.controllers;

import com.huy.simple_movie_review.dto.ProfileDto;
import com.huy.simple_movie_review.entities.Profile;
import com.huy.simple_movie_review.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateProfile(@RequestBody ProfileDto profileDto){
        Profile profile = profileService.updateProfile(profileDto);
        return ResponseEntity.ok(profile);
    }
}
