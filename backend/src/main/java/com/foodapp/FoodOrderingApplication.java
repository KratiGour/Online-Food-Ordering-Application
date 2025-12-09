package com.foodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MAIN APPLICATION CLASS
 * 
 * This is the entry point of your Spring Boot application.
 * When you run this class, your entire backend server starts.
 * 
 * @SpringBootApplication annotation does 3 things:
 * 1. @Configuration - Marks this as a configuration class
 * 2. @EnableAutoConfiguration - Automatically configures Spring based on dependencies
 * 3. @ComponentScan - Scans for other components in this package
 */
@SpringBootApplication
public class FoodOrderingApplication {
    
    /**
     * Main method - Starting point of Java application
     * 
     * @param args - Command line arguments (we don't use them here)
     */
    public static void main(String[] args) {
        // This line starts the Spring Boot application
        SpringApplication.run(FoodOrderingApplication.class, args);
        
        System.out.println("========================================");
        System.out.println("🚀 Food Ordering Backend Started!");
        System.out.println("📍 Running on: http://localhost:8080");
        System.out.println("========================================");
    }
}
