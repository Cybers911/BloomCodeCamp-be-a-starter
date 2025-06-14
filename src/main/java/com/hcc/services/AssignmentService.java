// File: services/AssignmentService.java
package com.hcc.services;

import com.hcc.dtos.AssignmentDTO;
import com.hcc.entities.Assignment;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public List<AssignmentDTO> getAllAssignments() {
        return assignmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AssignmentDTO getAssignmentById(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        return convertToDTO(assignment);
    }

    public AssignmentDTO createAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = convertToEntity(assignmentDTO);
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return convertToDTO(savedAssignment);
    }

    // Helpers to convert between Assignment and AssignmentDTO
    private AssignmentDTO convertToDTO(Assignment assignment) {
        return new AssignmentDTO(
                assignment.getId(),
                assignment.getStatus(),
                assignment.getNumber(),
                assignment.getGithubUrl(),
                assignment.getBranch(),
                assignment.getReviewVideoUrl()
        );
    }

    private Assignment convertToEntity(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();
        assignment.setStatus(assignmentDTO.getStatus());
        assignment.setNumber(assignmentDTO.getNumber());
        assignment.setGithubUrl(assignmentDTO.getGithubUrl());
        assignment.setBranch(assignmentDTO.getBranch());
        assignment.setReviewVideoUrl(assignmentDTO.getReviewVideoUrl());
        return assignment;
    }
}