package com.example.dto;

public class LoginResponse {

    private String token;
    private String role; // ✅ added role to match frontend

    // Default constructor (needed for JSON serialization)
    public LoginResponse() { }

    // Constructor
    public LoginResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    // ---------------- GETTERS & SETTERS ----------------
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
