package com.hcc;
import com.hcc.controllers.UserController;
import com.hcc.dtos.UserDTO;
import com.hcc.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserControllerMockTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserUsingMockService() {
        // Given
        UserDTO mockUser = new UserDTO("mockUser");

        // When
        when(userService.createUser(mockUser)).thenReturn(mockUser);
        UserDTO result = userController.createUser(mockUser);

        // Then
        assertNotNull(result);
        verify(userService, times(1)).createUser(mockUser);
    }
}