package com.foodapp.repository;

import com.foodapp.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * ORDER REPOSITORY
 * 
 * This interface handles all database operations for Orders.
 * 
 * MongoRepository<Order, String> means:
 * - Order = The model we're working with
 * - String = Type of ID field
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    
    /**
     * Find all orders by a specific user
     * 
     * HOW IT WORKS:
     * - Spring creates query: db.orders.find({ userId: "user123" })
     * 
     * Example:
     * List<Order> myOrders = orderRepository.findByUserId("user123");
     * 
     * WHEN USED: "My Orders" page - show user's order history
     */
    List<Order> findByUserId(String userId);
    
    /**
     * Find orders by status
     * 
     * HOW IT WORKS:
     * - Spring creates query: db.orders.find({ status: "PENDING" })
     * 
     * Example:
     * List<Order> pendingOrders = orderRepository.findByStatus("PENDING");
     * 
     * WHEN USED: Admin dashboard - see all pending/confirmed/delivered orders
     */
    List<Order> findByStatus(String status);
    
    /**
     * Find user's orders sorted by date (newest first)
     * 
     * HOW IT WORKS:
     * - "OrderBy" = sort results
     * - "OrderDateDesc" = sort by orderDate in descending order (newest first)
     * 
     * Example:
     * List<Order> recentOrders = orderRepository.findByUserIdOrderByOrderDateDesc("user123");
     * 
     * WHEN USED: Show user's recent orders first
     */
    List<Order> findByUserIdOrderByOrderDateDesc(String userId);
    
    /**
     * Count orders by user
     * 
     * HOW IT WORKS:
     * - Spring creates query: db.orders.count({ userId: "user123" })
     * 
     * Example:
     * long totalOrders = orderRepository.countByUserId("user123");
     * 
     * WHEN USED: Display "You have placed 5 orders"
     */
    long countByUserId(String userId);
    
    /**
     * INHERITED METHODS (from MongoRepository):
     * 
     * - save(order)             → Save new order
     * - findById(id)            → Get order by ID
     * - findAll()               → Get all orders (admin)
     * - deleteById(id)          → Cancel order
     * - count()                 → Total orders in system
     * 
     * Example usage:
     * Order order = new Order();
     * order.setUserId("user123");
     * order.setTotalAmount(599.0);
     * orderRepository.save(order);  // Saves order to database
     */
}
