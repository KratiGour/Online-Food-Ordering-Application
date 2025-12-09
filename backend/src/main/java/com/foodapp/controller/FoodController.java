package com.foodapp.controller;

import com.foodapp.model.Food;
import com.foodapp.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/foods")
@CrossOrigin(origins = "*")
public class FoodController {
    
    @Autowired
    private FoodService foodService;
    
  
    @GetMapping
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> foods = foodService.getAllFoods();
        return ResponseEntity.ok(foods);
    }
    
   
    @GetMapping("/available")
    public ResponseEntity<List<Food>> getAvailableFoods() {
        List<Food> foods = foodService.getAllAvailableFoods();
        return ResponseEntity.ok(foods);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodById(@PathVariable String id) {
        return foodService.getFoodById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    
    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFoods(@RequestParam String keyword) {
        List<Food> foods = foodService.searchFoods(keyword);
        return ResponseEntity.ok(foods);
    }
    
   
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Food>> getFoodsByCategory(@PathVariable String category) {
        List<Food> foods = foodService.getFoodsByCategory(category);
        return ResponseEntity.ok(foods);
    }
    
  
    @PostMapping
    public ResponseEntity<?> addFood(@RequestBody Food food) {
        try {
            Food savedFood = foodService.addFood(food);
            return ResponseEntity.ok(savedFood);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable String id, @RequestBody Food food) {
        try {
            Food updatedFood = foodService.updateFood(id, food);
            return ResponseEntity.ok(updatedFood);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    
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
