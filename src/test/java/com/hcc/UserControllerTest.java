package com.hcc;

import com.hcc.controllers.UserController;
import com.hcc.dtos.UserDTO;
import com.hcc.entities.User;
import com.hcc.exceptions.UserAlreadyExistsException;
import com.hcc.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void shouldReturnListOfUsers_whenGetAllUsersIsCalled() {
        // Arrange
        List<User> users = Collections.singletonList(new User("testUser", "hashedPassword", Collections.emptyList()));
        when(userService.getAllUsers()).thenReturn(users);

        // Act
        List<UserDTO> result = userController.getAllUsers();

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUsername()).isEqualTo("testUser");
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void shouldCreateUser_whenValidUserDTOIsPassed() {
        // Arrange
        UserDTO userDTO = new UserDTO(null, "newUser", "password", null);
        User newUser = new User(userDTO.getUsername(), "hashedPassword", Collections.emptyList());
        when(userService.createUser(userDTO)).thenReturn(newUser);

        // Act
        UserDTO result = userController.createUser(userDTO);

        // Assert
        assertThat(result.getUsername()).isEqualTo("newUser");
        verify(userService, times(1)).createUser(userDTO);
    }

    @Test
    void shouldThrowException_whenUsernameAlreadyExists() {
        // Arrange
        UserDTO userDTO = new UserDTO(null, "existingUser", "password", null);
        when(userService.createUser(userDTO)).thenThrow(new UserAlreadyExistsException("User already exists"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userController.createUser(userDTO));
        verify(userService, times(1)).createUser(userDTO);
    }
}