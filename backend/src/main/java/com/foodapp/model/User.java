package com.foodapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;


@Document(collection = "users")
public class User {
    
    // @Id means this is the unique identifier (like Aadhaar number)
    @Id
    private String id;
    
    private String name;           // User's full name
    
    // @Indexed(unique = true) means no two users can have same email
    @Indexed(unique = true)
    private String email;          // User's email (used for login)
    
    private String password;       // Encrypted password
    private String phone;          // Contact number
    private String address;        // Delivery address
    
    // Role: "USER" or "ADMIN"
    private String role = "USER";  // Default is USER
    
    // CONSTRUCTORS
    
    // Empty constructor (required by Spring)
    public User() {}
    
    // Constructor with all fields (for easy object creation)
    public User(String name, String email, String password, String phone, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }
    
    // GETTERS AND SETTERS
    // These allow us to read and modify private fields
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
