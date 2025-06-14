// File: dtos/AssignmentDTO.java
package com.hcc.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

public class AssignmentDTO {

    private Long id;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @NotNull(message = "Number must be specified")
    private Integer number;

    private String githubUrl;
    private String branch;
    private String reviewVideoUrl;

    // Default no-args constructor (required for frameworks like Jackson)
    public AssignmentDTO() {
    }

    // Full constructor
    public AssignmentDTO(Long id, String status, Integer number, String githubUrl, String branch, String reviewVideoUrl) {
        this.id = id;
        this.status = status;
        this.number = number;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.reviewVideoUrl = reviewVideoUrl;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getReviewVideoUrl() {
        return reviewVideoUrl;
    }

    public void setReviewVideoUrl(String reviewVideoUrl) {
        this.reviewVideoUrl = reviewVideoUrl;
    }

    // Overridden toString method for logging and debugging
    @Override
    public String toString() {
        return "AssignmentDTO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", number=" + number +
                ", githubUrl='" + githubUrl + '\'' +
                ", branch='" + branch + '\'' +
                ", reviewVideoUrl='" + reviewVideoUrl + '\'' +
                '}';
    }

    // Overridden equals method for object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentDTO that = (AssignmentDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(status, that.status) &&
                Objects.equals(number, that.number) &&
                Objects.equals(githubUrl, that.githubUrl) &&
                Objects.equals(branch, that.branch) &&
                Objects.equals(reviewVideoUrl, that.reviewVideoUrl);
    }

    // Overridden hashCode method for hashing
    @Override
    public int hashCode() {
        return Objects.hash(id, status, number, githubUrl, branch, reviewVideoUrl);
    }
}