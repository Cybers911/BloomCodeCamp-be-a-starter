// File: services/UserService.java
package com.hcc.services;

import com.hcc.dtos.UserDTO;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User(
                userDTO.getUsername(),
                userDTO.getPassword(),
                null // assuming authorities will be set separately
        );
        return userRepository.save(newUser);
    }
}