package com.hcc;



import com.hcc.dtos.UserDTO;
import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class UserIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userRepository.deleteAll(); // Clear database before each test
    }

    @Test
    void shouldCreateUser_whenValidRequestIsSent() throws Exception {
        // Arrange
        String userJson = """
                {
                    "username": "newUser",
                    "password": "password",
                    "authorities": ["ROLE_USER"]
                }
                """;

        // Act & Assert
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("newUser")));

        // Verify data is added to the database
        User savedUser = userRepository.findByUsername("newUser").get();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        assertThat(passwordEncoder.matches("password", savedUser.getPassword())).isTrue();
    }

    @Test
    void shouldReturnError_whenUsernameAlreadyExists() throws Exception {
        // Arrange
        userRepository.save(new User("existingUser", "password", Collections.emptyList()));
        String userJson = """
                {
                    "username": "existingUser",
                    "password": "password",
                    "authorities": ["ROLE_USER"]
                }
                """;

        // Act & Assert
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().is5xxServerError());
    }
}
