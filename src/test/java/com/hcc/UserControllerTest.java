package com.hcc;




import com.hcc.controllers.UserController;
import com.hcc.dtos.UserDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
    }

    @Test
    void testGetAllUsers() {
        // Given
        List<UserDTO> mockUsers = Arrays.asList(new UserDTO("user1"), new UserDTO("user2"));

        // When
        when(userController.getAllUsers()).thenReturn(mockUsers);

        // Then
        List<UserDTO> users = userController.getAllUsers();
        assertEquals(2, users.size());
        assertEquals("user1", users.get(0).getUsername());
    }

    @Test
    void testGetUserByUsername() {
        // Given
        String username = "testUser";
        UserDTO mockUser = new UserDTO(username);

        // When
        when(userController.getUserByUsername(username)).thenReturn(mockUser);

        // Then
        UserDTO user = userController.getUserByUsername(username);
        assertEquals(username, user.getUsername());
    }
}