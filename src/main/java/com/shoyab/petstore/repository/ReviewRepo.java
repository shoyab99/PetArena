package com.shoyab.petstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoyab.petstore.model.Review;
@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer>{
    List<Review> findByProductProductId(int productId);
    
}
