package com.hcc;
import com.hcc.controllers.UserController;


import com.hcc.dtos.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserControllerIntegrationTest {

    @Autowired
    private UserController userController;

    @Test
    void testGetAllUsersIntegration() {
        // When
        List<UserDTO> users = userController.getAllUsers();

        // Then
        assertEquals(HttpStatus.OK.value(), 200);
        assertEquals(users.size(), 0); // Assuming no users for now
    }

    @Test
    void testCreateUserIntegration() {
        // Given
        UserDTO newUser = new UserDTO("newUser");

        // When
        UserDTO createdUser = userController.createUser(newUser);

        // Then
        assertEquals("newUser", createdUser.getUsername());
    }
}
