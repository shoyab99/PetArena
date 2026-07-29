package com.shoyab.petstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoyab.petstore.model.Category;
@Repository

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	

}
