package com.foodapp.controller;

import com.foodapp.model.Order;
import com.foodapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * ORDER CONTROLLER
 * 
 * Handles all order-related operations
 * Base URL: /api/orders
 */
@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    /**
     * PLACE NEW ORDER
     * POST /api/orders
     * Body: {
     *   "userId": "user123",
     *   "items": [
     *     { "foodId": "food1", "quantity": 2 },
     *     { "foodId": "food2", "quantity": 1 }
     *   ],
     *   "deliveryAddress": "123 Street"
     * }
     */
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        try {
            Order placedOrder = orderService.placeOrder(order);
            return ResponseEntity.ok(placedOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * GET USER'S ORDERS
     * GET /api/orders/user/{userId}
     * Example: GET /api/orders/user/user123
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable String userId) {
        List<Order> orders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(orders);
    }
    
    /**
     * GET ALL ORDERS (Admin only)
     * GET /api/orders
     */
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    
    /**
     * GET ORDERS BY STATUS (Admin only)
     * GET /api/orders/status/{status}
     * Example: GET /api/orders/status/PENDING
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String status) {
        List<Order> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }
    
    /**
     * UPDATE ORDER STATUS (Admin only)
     * PUT /api/orders/{id}/status
     * Body: { "status": "CONFIRMED" }
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable String id, @RequestBody StatusRequest request) {
        try {
            Order updatedOrder = orderService.updateOrderStatus(id, request.getStatus());
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * CANCEL ORDER
     * DELETE /api/orders/{id}/user/{userId}
     */
    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<?> cancelOrder(@PathVariable String id, @PathVariable String userId) {
        try {
            orderService.cancelOrder(id, userId);
            return ResponseEntity.ok("Order cancelled successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * STATUS REQUEST DTO
     */
    static class StatusRequest {
        private String status;
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}
