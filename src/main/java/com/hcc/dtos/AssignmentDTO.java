package com.hcc.dtos;

import com.hcc.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AssignmentDTO {

    // Fields
    private Long id;
    private String assignmentName;
    private String status;
    private LocalDate dueDate;
    private String associatedUsername;

    // Constants for assignment statuses
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_COMPLETED = "Completed";

    // Constructor: Initializes from fields
    public AssignmentDTO(Long id, String assignmentName, String status, LocalDate dueDate, String associatedUsername) {
        this.id = id;
        this.assignmentName = assignmentName;
        this.status = status;
        this.dueDate = dueDate;
        this.associatedUsername = associatedUsername;
    }

    // Constructor: Initializes from Assignment entity
    public AssignmentDTO(com.hcc.entities.Assignment assignment, UserDTO userDTO) {
        this.id = assignment.getId();
        this.assignmentName = assignment.getAssignmentName();
        this.status = assignment.getStatus();
        this.dueDate = assignment.getDueDate();
        this.associatedUsername = userDTO.getUsername();
    }

    public AssignmentDTO(Long id, String status, Integer number, String githubUrl, String branch, String reviewVideoUrl) {
    }

    // Factory method to convert from entity with a UserDTO
    public static AssignmentDTO fromEntity(com.hcc.entities.Assignment assignment, UserDTO userDTO) {
        return new AssignmentDTO(
                assignment.getId(),
                assignment.getAssignmentName(),
                assignment.getStatus(),
                assignment.getDueDate(),
                userDTO.getUsername()
        );
    }

    // Converts DTO to entity
    public com.hcc.entities.Assignment toAssignmentEntity(User user) {
        com.hcc.entities.Assignment assignment = new com.hcc.entities.Assignment();
        assignment.setId(this.id);
        assignment.setAssignmentName(this.assignmentName);
        assignment.setStatus(this.status);
        assignment.setDueDate(this.dueDate);
        assignment.setUser(user);
        return assignment;
    }

    public Integer getNumber() {
    }

    public String getGithubUrl() {
    }

    public String getBranch() {
    }

    public String getReviewVideoUrl() {
    }
    public String getStatus() {
    }

    // Additional methods for assignment-specific operations...


}