package com.hcc;


import com.hcc.dtos.UserDTO;
import com.hcc.entities.User;
import com.hcc.exceptions.UserAlreadyExistsException;
import com.hcc.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void shouldCreateUser_whenValidUserDTOIsGiven() {
        // Arrange
        UserDTO userDTO = new UserDTO(null, "newUser", "password", null);
        when(userRepository.findByUsername("newUser")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = userService.createUser(userDTO);

        // Assert
        assertThat(result.getUsername()).isEqualTo("newUser");
        assertThat(result.getAuthorities()).hasSize(1);
        assertThat(result.getPassword()).isNotEqualTo("password"); // Password should be hashed
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldThrowException_whenUsernameAlreadyExists() {
        // Arrange
        UserDTO userDTO = new UserDTO(null, "existingUser", "password", null);
        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(new User(userDTO.getUsername(), hashedPassword, authorities)));

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(userDTO));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldHashPassword_whenUserIsCreated() {
        // Arrange
        UserDTO userDTO = new UserDTO(null, "newUser", "password", null);
        when(userRepository.findByUsername("newUser")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = userService.createUser(userDTO);

        // Assert
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        assertThat(passwordEncoder.matches("password", result.getPassword())).isTrue(); // Check if password is hashed correctly
    }
}

