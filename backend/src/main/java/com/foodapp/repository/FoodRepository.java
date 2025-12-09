package com.foodapp.repository;

import com.foodapp.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * FOOD REPOSITORY
 * 
 * This interface handles all database operations for Food items.
 * 
 * MongoRepository<Food, String> means:
 * - Food = The model we're working with
 * - String = Type of ID field
 */
@Repository
public interface FoodRepository extends MongoRepository<Food, String> {
    
    /**
     * Find all foods by category
     * 
     * HOW IT WORKS:
     * - Spring creates query: db.foods.find({ category: "Pizza" })
     * 
     * Example:
     * List<Food> pizzas = foodRepository.findByCategory("Pizza");
     * 
     * WHEN USED: When user filters by category (Pizza, Burger, Dessert)
     */
    List<Food> findByCategory(String category);
    
    /**
     * Find foods by availability
     * 
     * HOW IT WORKS:
     * - Spring creates query: db.foods.find({ available: true })
     * 
     * Example:
     * List<Food> availableFoods = foodRepository.findByAvailable(true);
     * 
     * WHEN USED: Show only available items to customers
     */
    List<Food> findByAvailable(Boolean available);
    
    /**
     * Search foods by name (case-insensitive, partial match)
     * 
     * HOW IT WORKS:
     * - "Containing" = partial match (like SQL LIKE '%pizza%')
     * - "IgnoreCase" = case-insensitive (Pizza = pizza = PIZZA)
     * 
     * Example:
     * foodRepository.findByNameContainingIgnoreCase("pizza");
     * Returns: "Margherita Pizza", "Pepperoni Pizza", "Pizza Special"
     * 
     * WHEN USED: Search bar functionality
     */
    List<Food> findByNameContainingIgnoreCase(String name);
    
    /**
     * Find available foods by category
     * 
     * HOW IT WORKS:
     * - Combines two conditions with AND
     * - Query: db.foods.find({ category: "Pizza", available: true })
     * 
     * WHEN USED: Show only available pizzas/burgers/desserts
     */
    List<Food> findByCategoryAndAvailable(String category, Boolean available);
    
    /**
     * INHERITED METHODS (from MongoRepository):
     * 
     * - save(food)              → Add new food or update existing
     * - findById(id)            → Get food by ID
     * - findAll()               → Get all foods
     * - deleteById(id)          → Delete food (admin only)
     * - count()                 → Count total food items
     * 
     * Example usage:
     * Food pizza = new Food("Margherita", "Cheese pizza", 299.0, "Pizza");
     * foodRepository.save(pizza);  // Saves to database
     */
}
