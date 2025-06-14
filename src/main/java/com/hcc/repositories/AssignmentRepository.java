package com.hcc.repositories;

import com.hcc.entities.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    // Find assignments by their status (e.g., "Pending", "Completed", etc.)
    List<Assignment> findByStatus(String status);

    // Find a specific assignment by its GitHub URL
    Optional<Assignment> findByGithubUrl(String githubUrl);

    // Find all assignments associated with a specific user ID
    List<Assignment> findByUserId(Long userId);

    // Find assignments by status and user ID using a custom query
    @Query("SELECT a FROM Assignment a WHERE a.status = :status AND a.user.id = :userId")
    List<Assignment> findAssignmentsByStatusAndUserId(@Param("status") String status, @Param("userId") Long userId);

    // Enable retrieval of assignments based on status with pagination support
    @Query("SELECT a FROM Assignment a WHERE a.status = :status")
    List<Assignment> findAssignmentsByStatusPaged(@Param("status") String status, Pageable pageable);

    // Count the total number of assignments associated with a specific user
    @Query("SELECT COUNT(a) FROM Assignment a WHERE a.user.id = :userId")
    long countAssignmentsByUserId(@Param("userId") Long userId);

    // Find assignments that have null GitHub URLs or are not yet submitted
    @Query("SELECT a FROM Assignment a WHERE a.githubUrl IS NULL")
    List<Assignment> findAssignmentsWithoutGithubUrl();
}