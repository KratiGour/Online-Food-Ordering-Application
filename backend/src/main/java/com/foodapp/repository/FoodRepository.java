package com.foodapp.repository;

import com.foodapp.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface FoodRepository extends MongoRepository<Food, String> {
    
   
    List<Food> findByCategory(String category);
    
   
    List<Food> findByAvailable(Boolean available);
    
   
    List<Food> findByNameContainingIgnoreCase(String name);
    
   
    List<Food> findByCategoryAndAvailable(String category, Boolean available);
  
}
