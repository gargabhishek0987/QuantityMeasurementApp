package com.app.quanitymeasurement.controller;

import com.app.quanitymeasurement.entity.User;
import com.app.quanitymeasurement.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = authService.login(user);

        return ResponseEntity.ok(
                Map.of("token", token, "username", user.getUsername())
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        String message = authService.signup(user);

        return ResponseEntity.ok(
                Map.of("message", message)
        );
    }
}