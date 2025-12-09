package com.foodapp.repository;

import com.foodapp.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    
    
    List<Order> findByUserId(String userId);
    
  
    List<Order> findByStatus(String status);
    
  
    List<Order> findByUserIdOrderByOrderDateDesc(String userId);
    
   
    long countByUserId(String userId);
    
   
}
