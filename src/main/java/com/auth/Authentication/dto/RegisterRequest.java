package com.auth.Authentication.dto;

public class RegisterRequest {
    private String username;
    private String email; // New field for email
    private String password;
    private String role; // Role can be "COACH", "ATHLETE", or "ADMIN"

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
