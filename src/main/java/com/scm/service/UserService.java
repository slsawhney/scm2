package com.scm.service;

import com.scm.entity.User;
import com.scm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // generates constructor for final fields
public class UserService {

    private final UserRepository userRepository;

    // Save new user
    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error creating user: " + e.getMessage(), e);
        }
    }

    // Update existing user
    public User updateUser(Long id, User userDetails) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setAbout(userDetails.getAbout());
            user.setPhone(userDetails.getPhone());

            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating user: " + e.getMessage(), e);
        }
    }

    // Find user by ID
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    // Delete user by ID
    public void delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        userRepository.delete(user);
    }

    // Check if user exists by ID
    public boolean existsById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        return userRepository.existsById(userId);
    }

    // Check if user exists by Email
    public boolean isUserExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
