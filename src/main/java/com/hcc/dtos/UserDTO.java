package com.hcc.dtos;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    private String password;  // Include password for creation
    private List<String> authorities;

    public UserDTO(Long id, String username, List<String> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }

    public UserDTO(Long id, String username, String password, List<String> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // Hash password using BCrypt
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
