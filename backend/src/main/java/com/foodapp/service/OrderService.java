package com.foodapp.service;

import com.foodapp.model.Order;
import com.foodapp.model.OrderItem;
import com.foodapp.model.Food;
import com.foodapp.repository.OrderRepository;
import com.foodapp.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * ORDER SERVICE
 * 
 * Handles all order-related business logic:
 * - Place new orders
 * - Calculate totals
 * - Get order history
 * - Update order status (admin)
 */
@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private FoodRepository foodRepository;
    
    /**
     * PLACE NEW ORDER
     * 
     * Steps:
     * 1. Validate all food items exist and are available
     * 2. Calculate total amount
     * 3. Create order with PENDING status
     * 4. Save to database
     * 
     * @param order - Order object with items
     * @return Saved order with generated ID
     */
    public Order placeOrder(Order order) throws Exception {
        // Validate and calculate total
        double totalAmount = 0.0;
        
        for (OrderItem item : order.getItems()) {
            // Check if food exists
            Optional<Food> foodOpt = foodRepository.findById(item.getFoodId());
            
            if (!foodOpt.isPresent()) {
                throw new Exception("Food item not found: " + item.getFoodId());
            }
            
            Food food = foodOpt.get();
            
            // Check if food is available
            if (!food.getAvailable()) {
                throw new Exception("Food item not available: " + food.getName());
            }
            
            // Set food name and price in order item
            item.setFoodName(food.getName());
            item.setPrice(food.getPrice());
            
            // Calculate subtotal for this item
            totalAmount += item.getSubtotal();
        }
        
        // Set order details
        order.setTotalAmount(totalAmount);
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());
        
        // Save and return
        return orderRepository.save(order);
    }
    
    /**
     * GET USER'S ORDERS
     * Returns orders sorted by date (newest first)
     */
    public List<Order> getUserOrders(String userId) {
        return orderRepository.findByUserIdOrderByOrderDateDesc(userId);
    }
    
    /**
     * GET ALL ORDERS (Admin only)
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    /**
     * GET ORDERS BY STATUS (Admin only)
     * Example: Get all PENDING orders
     */
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
    
    /**
     * GET ORDER BY ID
     */
    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }
    
    /**
     * UPDATE ORDER STATUS (Admin only)
     * Status flow: PENDING → CONFIRMED → DELIVERED
     */
    public Order updateOrderStatus(String orderId, String newStatus) throws Exception {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        
        if (!orderOpt.isPresent()) {
            throw new Exception("Order not found");
        }
        
        Order order = orderOpt.get();
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
    
    /**
     * CANCEL ORDER
     * Only PENDING orders can be cancelled
     */
    public void cancelOrder(String orderId, String userId) throws Exception {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        
        if (!orderOpt.isPresent()) {
            throw new Exception("Order not found");
        }
        
        Order order = orderOpt.get();
        
        // Check if order belongs to user
        if (!order.getUserId().equals(userId)) {
            throw new Exception("Unauthorized to cancel this order");
        }
        
        // Only PENDING orders can be cancelled
        if (!"PENDING".equals(order.getStatus())) {
            throw new Exception("Only pending orders can be cancelled");
        }
        
        orderRepository.deleteById(orderId);
    }
}
