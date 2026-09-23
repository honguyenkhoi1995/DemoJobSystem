package com.jobportal.controller;

import com.jobportal.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/{id}/unlock")
    public String unlockUser(@PathVariable Long id) {
        return userService.unlockUser(id);
    }
}
