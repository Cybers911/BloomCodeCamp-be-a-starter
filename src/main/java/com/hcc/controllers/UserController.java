// File: controllers/UserController.java
package com.hcc.controllers;

import com.hcc.dtos.UserDTO;
import com.hcc.entities.User;
import com.hcc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getAuthorities()
                                .stream()
                                .map(auth -> auth.getAuthority())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    // Get user by username
    @GetMapping("/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getAuthorities()
                                .stream()
                                .map(auth -> auth.getAuthority())
                                .collect(Collectors.toList())
                ))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Create a new user
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User newUser = userService.createUser(userDTO);
        return new UserDTO(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getAuthorities()
                        .stream()
                        .map(auth -> auth.getAuthority())
                        .collect(Collectors.toList())
        );
    }
}