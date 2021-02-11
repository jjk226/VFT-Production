package com.james.springboot.vftproductiontracker.controller;

import com.james.springboot.vftproductiontracker.security.UserRepository;
import com.james.springboot.vftproductiontracker.security.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicRestApiController {
    private UserRepository userRepository;

    public PublicRestApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> allUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/users/{username}")
    public User findByUsername(@PathVariable("username") String username) {
        return userRepository.findByUsername(username);
    }

}
