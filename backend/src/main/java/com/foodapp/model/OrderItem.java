package com.foodapp.model;

/**
 * ORDER ITEM MODEL
 * 
 * This represents ONE item in an order.
 * Example: "2x Margherita Pizza @ ₹299 each"
 * 
 * This is NOT a separate collection in MongoDB.
 * It's embedded inside Order document.
 */
public class OrderItem {
    
    private String foodId;          // Which food item?
    private String foodName;        // Name of food (stored for history)
    private Integer quantity;       // How many?
    private Double price;           // Price per item
    
    // CONSTRUCTORS
    
    public OrderItem() {}
    
    public OrderItem(String foodId, String foodName, Integer quantity, Double price) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
    }
    
    // GETTERS AND SETTERS
    
    public String getFoodId() {
        return foodId;
    }
    
    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }
    
    public String getFoodName() {
        return foodName;
    }
    
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    /**
     * Calculate total price for this item
     * Example: 2 pizzas @ ₹299 = ₹598
     */
    public Double getSubtotal() {
        return quantity * price;
    }
}
