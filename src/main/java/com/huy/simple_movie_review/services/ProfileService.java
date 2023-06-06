package com.huy.simple_movie_review.services;

import com.huy.simple_movie_review.dto.ProfileDto;
import com.huy.simple_movie_review.entities.Profile;
import com.huy.simple_movie_review.exception.DataNotFoundException;
import com.huy.simple_movie_review.repositories.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepo;

    @Autowired
    public ProfileService(ProfileRepository profileRepo){
        this.profileRepo = profileRepo;
    }

    @Transactional
    public Profile updateProfile(ProfileDto profileDto){
        if(!profileRepo.existsById(profileDto.getId())){
            throw new DataNotFoundException("Profile with id " + profileDto.getId() + " not exist");
        }

        Profile profile = profileRepo.findById(profileDto.getId()).orElse(null);
        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setBirthdate(profileDto.getBirthdate());
        profile.setGender(profileDto.getGender());

        profile = profileRepo.save(profile);
        return profile;
    }
}
