package com.hcc.services;

import com.hcc.dtos.UserDTO;
import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.exceptions.UserAlreadyExistsException;
import com.hcc.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(UserDTO userDTO) {
        // Check if the username already exists
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with username already exists!");
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());

        // Assign default authorities if none provided
        List<Authority> authorities = Optional.ofNullable(userDTO.getAuthorities())
                .map(auths -> auths.stream()
                        .map(Authority::new)
                        .toList())
                .orElse(Collections.singletonList(new Authority("ROLE_USER")));

        // Create and save the user
        User newUser = new User(userDTO.getUsername(), hashedPassword, authorities);
        return userRepository.save(newUser);
    }
}