package com.auth.Authentication.Controller;

import com.auth.Authentication.dto.LoginRequest;
import com.auth.Authentication.dto.RegisterRequest;
import com.auth.Authentication.entity.User;
import com.auth.Authentication.Services.UserService;
import com.auth.Authentication.exception.ApiException;
import com.auth.Authentication.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            userService.registerUser(request);
            return ResponseEntity.ok("Signup successful");
        } catch (ApiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during signup: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = userService.authenticateUser(request);
            String token = jwtTokenProvider.generateToken(user.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "You are logged in successfully");
            response.put("token", token);
            response.put("roles", user.getRoles()); // Include roles in response

            return ResponseEntity.ok(response);
        } catch (ApiException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during login: " + e.getMessage());
        }

    }
}

