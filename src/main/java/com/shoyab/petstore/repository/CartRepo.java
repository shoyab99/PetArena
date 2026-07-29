package com.shoyab.petstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoyab.petstore.model.Cart;
import com.shoyab.petstore.model.Users;
@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	 Optional<Cart> findByUser(Users user);
}
