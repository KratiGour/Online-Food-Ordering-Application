package com.foodapp.service;

import com.foodapp.model.User;
import com.foodapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * USER SERVICE
 * 
 * Handles all user-related business logic:
 * - User registration with password encryption
 * - User login validation
 * - Finding users
 * 
 * @Service tells Spring this is a service component
 */
@Service
public class UserService {
    
    // Inject dependencies (Spring provides these automatically)
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    /**
     * REGISTER NEW USER
     * 
     * Steps:
     * 1. Check if email already exists
     * 2. Encrypt password (never store plain passwords!)
     * 3. Save user to database
     * 
     * @param user - User object with name, email, password
     * @return Saved user (with generated ID)
     * @throws Exception if email already exists
     */
    public User registerUser(User user) throws Exception {
        // Check if email already registered
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new Exception("Email already registered!");
        }
        
        // Encrypt password using BCrypt
        // Example: "password123" → "$2a$10$xYz..." (60 character hash)
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        
        // Set default role if not specified
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        
        // Save to database and return
        return userRepository.save(user);
    }
    
    /**
     * LOGIN USER
     * 
     * Steps:
     * 1. Find user by email
     * 2. Check if password matches (compare encrypted passwords)
     * 3. Return user if valid
     * 
     * @param email - User's email
     * @param password - Plain text password from login form
     * @return User object if credentials valid
     * @throws Exception if credentials invalid
     */
    public User loginUser(String email, String password) throws Exception {
        // Find user by email
        Optional<User> userOpt = userRepository.findByEmail(email);
        
        if (!userOpt.isPresent()) {
            throw new Exception("Invalid email or password");
        }
        
        User user = userOpt.get();
        
        // Check if password matches
        // passwordEncoder.matches() compares plain password with encrypted one
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Invalid email or password");
        }
        
        return user;
    }
    
    /**
     * FIND USER BY EMAIL
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    /**
     * FIND USER BY ID
     */
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}
