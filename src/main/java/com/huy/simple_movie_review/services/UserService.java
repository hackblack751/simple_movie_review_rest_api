package com.huy.simple_movie_review.services;

import com.huy.simple_movie_review.dto.ProfileDto;
import com.huy.simple_movie_review.dto.UserDto;
import com.huy.simple_movie_review.entities.Profile;
import com.huy.simple_movie_review.entities.User;
import com.huy.simple_movie_review.exception.DataNotFoundException;
import com.huy.simple_movie_review.repositories.ProfileRepository;
import com.huy.simple_movie_review.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final ProfileRepository profileRepo;

    @Autowired
    public UserService(UserRepository userRepo, ProfileRepository profileRepo){
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
    }

    @Transactional
    public User createUser(UserDto userDto){

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());

        if(user.getEmail() == null || user.getEmail().isBlank() || user.getUsername() == null ||
                user.getUsername().isBlank()){
            throw new RuntimeException("User's email and username is empty or null");
        }

        User existedUser = userRepo.findByEmail(user.getEmail()).orElse(null);
        if(existedUser != null){
            throw new RuntimeException("User exist by email");
        }

        existedUser = userRepo.findByUsername(user.getUsername()).orElse(null);
        if(existedUser != null){
            throw new RuntimeException("User exist by username");
        }

        Profile profile = new Profile();
        ProfileDto profileDto = userDto.getProfile();
        profile.setBirthdate(profileDto.getBirthdate());
        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setGender(profileDto.getGender());

        user.setProfile(profile);

        user = userRepo.save(user);


        return user;
    }

    @Transactional
    public User getUserById(Long id){
        if(!userRepo.existsById(id)){
            throw new DataNotFoundException("user with id " + id + " not exist");
        }
        User user = userRepo.findById(id).orElse(null);
        return user;
    }

}
