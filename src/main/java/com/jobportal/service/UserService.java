package com.jobportal.service;

import com.jobportal.model.User;
import com.jobportal.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String unlockUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return "User not found";
        }

        user.setAccountStatus("ACTIVE");
        user.setFailedAttempts(0);
        user.setLockedUntil(null);

        userRepository.save(user);
        return "Account unlocked successfully";
    }
}
