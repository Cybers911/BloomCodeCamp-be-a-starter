// File: dtos/UserDTO.java
package com.hcc.dtos;

import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    private List<String> authorities;

    public UserDTO(Long id, String username, List<String> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public List<String> getAuthorities() { return authorities; }
    public void setAuthorities(List<String> authorities) { this.authorities = authorities; }
}