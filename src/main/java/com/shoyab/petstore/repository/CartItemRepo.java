package com.shoyab.petstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoyab.petstore.model.Cart;
import com.shoyab.petstore.model.CartItem;
import com.shoyab.petstore.model.Product;
@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer>{
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    List<CartItem> findByCart(Cart cart);
}
