package com.hcc.services;

import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final CustomPasswordEncoder customPasswordEncoder;
    private final UserRepository userRepository;

    // Constructor-based dependency injection
    @Autowired
    public UserDetailServiceImpl(CustomPasswordEncoder customPasswordEncoder, UserRepository userRepository) {
        this.customPasswordEncoder = customPasswordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * Loads the user by username for authentication.
     *
     * @param username the username to look up
     * @return the UserDetails object containing authentication details
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user in the database by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Return a UserDetails object (using Spring Security's default implementation)
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList()) // Assign roles/authorities here if needed
                .build();
    }

    /**
     * Encodes a raw password using the CustomPasswordEncoder.
     *
     * @param rawPassword The raw password to encode
     * @return The encoded password
     */
    public String encodePassword(String rawPassword) {
        return customPasswordEncoder.encode(rawPassword);
    }
}