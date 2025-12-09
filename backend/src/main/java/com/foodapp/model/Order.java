package com.foodapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;


@Document(collection = "orders")
public class Order {
    
    @Id
    private String id;                          // Unique order ID
    
    private String userId;                      // Which user placed this order?
    private List<OrderItem> items;              // List of food items in this order
    private Double totalAmount;                 // Total price
    private String status;                      // Order status: PENDING, CONFIRMED, DELIVERED
    private LocalDateTime orderDate;            // When was order placed?
    private String deliveryAddress;             // Where to deliver?
    
    // CONSTRUCTORS
    
    public Order() {
        this.orderDate = LocalDateTime.now();   // Set current date/time
        this.status = "PENDING";                // Default status
    }
    
    // GETTERS AND SETTERS
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public List<OrderItem> getItems() {
        return items;
    }
    
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    
    public Double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
