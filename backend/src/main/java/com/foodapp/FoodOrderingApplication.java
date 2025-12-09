package com.foodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FoodOrderingApplication {
    
  
    public static void main(String[] args) {
        // This line starts the Spring Boot application
        SpringApplication.run(FoodOrderingApplication.class, args);
        
        System.out.println("========================================");
        System.out.println(" Food Ordering Backend Started!");
        System.out.println(" Running on: http://localhost:8080");
        System.out.println("========================================");
    }
}
