package com.jobportal.service;

import com.jobportal.model.User;
import com.jobportal.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(String username, String password, String role) {
        if (userRepository.findByUsername(username).isPresent()) {
            return "Username already exists";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role == null || role.isBlank() ? "USER" : role.toUpperCase());
        user.setFailedAttempts(0);
        user.setLockedUntil(null);
        user.setAccountStatus("ACTIVE");

        userRepository.save(user);
        return "REGISTER_SUCCESS";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return "Username or password is incorrect";
        }

        if ("LOCKED".equals(user.getAccountStatus())) {
            if (user.getLockedUntil() != null &&
                LocalDateTime.now().isBefore(user.getLockedUntil())) {
                return "Account is locked until " + user.getLockedUntil();
            }

            user.setAccountStatus("ACTIVE");
            user.setFailedAttempts(0);
            user.setLockedUntil(null);
            userRepository.save(user);
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            user.setFailedAttempts(0);
            userRepository.save(user);
            return "LOGIN_SUCCESS";
        }

        int attempts = user.getFailedAttempts() + 1;
        user.setFailedAttempts(attempts);

        if (attempts >= 3) {
            user.setAccountStatus("LOCKED");
            user.setLockedUntil(LocalDateTime.now().plusMinutes(15));
            userRepository.save(user);
            return "Account locked for 15 minutes";
        }

        userRepository.save(user);
        return "Wrong password. Attempt " + attempts + "/3";
    }
}
