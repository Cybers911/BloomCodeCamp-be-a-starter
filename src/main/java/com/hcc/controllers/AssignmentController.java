// File: controllers/AssignmentController.java
package com.hcc.controllers;

import com.hcc.dtos.AssignmentDTO;
import com.hcc.entities.Assignment;
import com.hcc.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // Get all assignments
    @GetMapping
    public List<AssignmentDTO> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    // Get assignment by ID
    @GetMapping("/{id}")
    public AssignmentDTO getAssignmentById(@PathVariable Long id) {
        return assignmentService.getAssignmentById(id);
    }

    // Create a new assignment
    @PostMapping
    public AssignmentDTO createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        return assignmentService.createAssignment(assignmentDTO);
    }
}