package com.foodapp.controller;

import com.foodapp.model.Food;
import com.foodapp.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * FOOD CONTROLLER
 * 
 * Handles all food-related operations
 * Base URL: /api/foods
 */
@RestController
@RequestMapping("/api/foods")
@CrossOrigin(origins = "*")
public class FoodController {
    
    @Autowired
    private FoodService foodService;
    
    /**
     * GET ALL FOODS
     * GET /api/foods
     */
    @GetMapping
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> foods = foodService.getAllFoods();
        return ResponseEntity.ok(foods);
    }
    
    /**
     * GET ALL AVAILABLE FOODS
     * GET /api/foods/available
     */
    @GetMapping("/available")
    public ResponseEntity<List<Food>> getAvailableFoods() {
        List<Food> foods = foodService.getAllAvailableFoods();
        return ResponseEntity.ok(foods);
    }
    
    /**
     * GET FOOD BY ID
     * GET /api/foods/{id}
     * Example: GET /api/foods/abc123
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodById(@PathVariable String id) {
        return foodService.getFoodById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * SEARCH FOODS
     * GET /api/foods/search?keyword=pizza
     */
    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFoods(@RequestParam String keyword) {
        List<Food> foods = foodService.searchFoods(keyword);
        return ResponseEntity.ok(foods);
    }
    
    /**
     * GET FOODS BY CATEGORY
     * GET /api/foods/category/{category}
     * Example: GET /api/foods/category/Pizza
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Food>> getFoodsByCategory(@PathVariable String category) {
        List<Food> foods = foodService.getFoodsByCategory(category);
        return ResponseEntity.ok(foods);
    }
    
    /**
     * ADD NEW FOOD (Admin only)
     * POST /api/foods
     * Body: { "name": "Pizza", "description": "Cheese pizza", "price": 299, "category": "Italian" }
     */
    @PostMapping
    public ResponseEntity<?> addFood(@RequestBody Food food) {
        try {
            Food savedFood = foodService.addFood(food);
            return ResponseEntity.ok(savedFood);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * UPDATE FOOD (Admin only)
     * PUT /api/foods/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable String id, @RequestBody Food food) {
        try {
            Food updatedFood = foodService.updateFood(id, food);
            return ResponseEntity.ok(updatedFood);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * DELETE FOOD (Admin only)
     * DELETE /api/foods/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable String id) {
        try {
            foodService.deleteFood(id);
            return ResponseEntity.ok("Food deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
