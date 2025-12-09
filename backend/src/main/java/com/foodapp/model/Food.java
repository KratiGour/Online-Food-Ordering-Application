package com.foodapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "foods")
public class Food {
    
    @Id
    private String id;              // Unique ID for each food item
    
    private String name;            // Food name (e.g., "Margherita Pizza")
    private String description;     // Description (e.g., "Cheese and tomato")
    private Double price;           // Price in rupees (e.g., 299.00)
    private String category;        // Category (e.g., "Pizza", "Burger", "Dessert")
    private String imageUrl;        // URL of food image
    private Boolean available = true; // Is this food available to order?
    
    // CONSTRUCTORS
    
    public Food() {}
    
    public Food(String name, String description, Double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
    
    // GETTERS AND SETTERS
    
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public Boolean getAvailable() {
        return available;
    }
    
    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
