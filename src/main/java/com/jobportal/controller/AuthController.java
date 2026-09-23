package com.jobportal.controller;

import com.jobportal.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false) String role) {
        return authService.register(username, password, role);
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password) {
        return authService.login(username, password);
    }
}
