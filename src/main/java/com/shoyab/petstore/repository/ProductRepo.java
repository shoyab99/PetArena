package com.shoyab.petstore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoyab.petstore.model.Product;
@Repository

public interface ProductRepo extends JpaRepository<Product, Integer> {
	List<Product> findByProductNameContainingIgnoreCase(String name);
	List<Product> findByProductNameContainingIgnoreCaseAndActiveTrue(String name);
	List<Product> findAllByOrderByProductIdDesc(Pageable pageable);
//	@Query("select p from Product p where p.price > ?1")
//	List<Product> findExpensiveProducts(double price);
	@Query("select p from Product p order by p.price asc")
	List<Product> ascending();
	Page<Product> findByActiveTrue(Pageable pageable);

	List<Product> findByActiveTrue();

	List<Product> findByActiveTrueOrderByProductIdDesc(Pageable pageable);
}
