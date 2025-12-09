package com.foodapp.service;

import com.foodapp.model.Food;
import com.foodapp.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * FOOD SERVICE
 * 
 * Handles all food-related business logic:
 * - Get all foods or filter by category
 * - Add/update/delete food items (admin)
 * - Search foods
 */
@Service
public class FoodService {
    
    @Autowired
    private FoodRepository foodRepository;
    
    /**
     * GET ALL AVAILABLE FOODS
     * Shows only items that are available to order
     */
    public List<Food> getAllAvailableFoods() {
        return foodRepository.findByAvailable(true);
    }
    
    /**
     * GET ALL FOODS (including unavailable)
     * Used by admin dashboard
     */
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
    
    /**
     * GET FOOD BY ID
     */
    public Optional<Food> getFoodById(String id) {
        return foodRepository.findById(id);
    }
    
    /**
     * GET FOODS BY CATEGORY
     * Example: Get all pizzas, burgers, etc.
     */
    public List<Food> getFoodsByCategory(String category) {
        return foodRepository.findByCategoryAndAvailable(category, true);
    }
    
    /**
     * SEARCH FOODS BY NAME
     * Case-insensitive partial match
     */
    public List<Food> searchFoods(String keyword) {
        return foodRepository.findByNameContainingIgnoreCase(keyword);
    }
    
    /**
     * ADD NEW FOOD ITEM (Admin only)
     * Validation: Price must be positive
     */
    public Food addFood(Food food) throws Exception {
        if (food.getPrice() <= 0) {
            throw new Exception("Price must be greater than 0");
        }
        return foodRepository.save(food);
    }
    
    /**
     * UPDATE FOOD ITEM (Admin only)
     */
    public Food updateFood(String id, Food updatedFood) throws Exception {
        Optional<Food> existingFood = foodRepository.findById(id);
        
        if (!existingFood.isPresent()) {
            throw new Exception("Food item not found");
        }
        
        updatedFood.setId(id);
        return foodRepository.save(updatedFood);
    }
    
    /**
     * DELETE FOOD ITEM (Admin only)
     */
    public void deleteFood(String id) throws Exception {
        if (!foodRepository.existsById(id)) {
            throw new Exception("Food item not found");
        }
        foodRepository.deleteById(id);
    }
    
    /**
     * TOGGLE AVAILABILITY (Admin only)
     * Mark food as available/unavailable
     */
    public Food toggleAvailability(String id) throws Exception {
        Optional<Food> foodOpt = foodRepository.findById(id);
        
        if (!foodOpt.isPresent()) {
            throw new Exception("Food item not found");
        }
        
        Food food = foodOpt.get();
        food.setAvailable(!food.getAvailable());
        return foodRepository.save(food);
    }
}
