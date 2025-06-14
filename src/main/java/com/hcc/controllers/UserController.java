package com.hcc.controllers;

import com.hcc.dtos.UserDTO;
import com.hcc.entities.User;
import com.hcc.exceptions.UserAlreadyExistsException;
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

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        try {
            User newUser = userService.createUser(userDTO);
            return mapToUserDTO(newUser);
        } catch (UserAlreadyExistsException e) {
            throw new RuntimeException("User with username '" + userDTO.getUsername() + "' already exists!", e);
        }
    }

    private UserDTO mapToUserDTO(User user) {
        List<String> authorities = user.getAuthorities()
                .stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());
        return new UserDTO(user.getId(), user.getUsername(), authorities);
    }
}