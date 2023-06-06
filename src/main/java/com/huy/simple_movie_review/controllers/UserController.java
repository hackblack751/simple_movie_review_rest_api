package com.huy.simple_movie_review.controllers;

import com.huy.simple_movie_review.dto.UserDto;
import com.huy.simple_movie_review.entities.User;
import com.huy.simple_movie_review.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto){
        User user = userService.createUser(userDto);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(name = "id") Long id){
        User  user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
