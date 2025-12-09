package com.foodapp.repository;

import com.foodapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * USER REPOSITORY
 * 
 * This interface handles all database operations for User.
 * 
 * WHY INTERFACE? 
 * - Spring Data MongoDB automatically creates the implementation
 * - You just define method names, Spring writes the code!
 * 
 * MongoRepository<User, String> means:
 * - User = The model we're working with
 * - String = Type of ID field (our User has String id)
 * 
 * @Repository tells Spring this is a database component
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    /**
     * Find user by email
     * 
     * HOW IT WORKS:
     * - Spring reads method name: "findByEmail"
     * - Automatically creates query: db.users.find({ email: "xyz@gmail.com" })
     * 
     * Optional<User> means:
     * - User might exist (Optional.of(user))
     * - User might not exist (Optional.empty())
     * - Prevents null pointer errors!
     * 
     * WHEN USED: During login - check if email exists
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Check if email already exists
     * 
     * HOW IT WORKS:
     * - Spring creates query: db.users.count({ email: "xyz@gmail.com" }) > 0
     * 
     * Returns:
     * - true if email exists
     * - false if email doesn't exist
     * 
     * WHEN USED: During registration - prevent duplicate emails
     */
    boolean existsByEmail(String email);
    
    /**
     * INHERITED METHODS (from MongoRepository):
     * 
     * You get these for FREE without writing any code:
     * 
     * - save(user)              → Save or update user
     * - findById(id)            → Find user by ID
     * - findAll()               → Get all users
     * - deleteById(id)          → Delete user by ID
     * - count()                 → Count total users
     * 
     * Example usage:
     * userRepository.save(newUser);           // Save user to database
     * User user = userRepository.findById("123"); // Get user with ID "123"
     */
}
