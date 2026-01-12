package com.example.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.entity.User;
import com.example.service.AuthService;
import com.example.utils.JWTUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS},
        allowCredentials = "true"
)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

  
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> optionalUser = authService.login(request.getUsername(), request.getPassword());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        User user = optionalUser.get();
        String token = JWTUtil.generateToken(user.getUsername(), user.getRole());

        // send token + role to frontend
        return ResponseEntity.ok(new LoginResponse(token, user.getRole()));
    }

  
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("LOGOUT_SUCCESS");
    }

 
    @GetMapping("/status")
    public ResponseEntity<Boolean> status() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(auth != null && auth.isAuthenticated());
    }

    // ---------------- ADMIN ACCESS ----------------
    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
        return ResponseEntity.ok("Admin data");
    }
}
