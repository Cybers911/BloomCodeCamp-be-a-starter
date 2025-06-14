package com.hcc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class DatabaseIntegrationTest {

    @org.testcontainers.junit.jupiter.Container
    private static JdbcDatabaseContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @MockBean
    private com.hcc.repositories.UserRepository userRepository;

    @Test
    void testDatabaseConnection() {
        assert(postgreSQLContainer.isRunning());
    }
}