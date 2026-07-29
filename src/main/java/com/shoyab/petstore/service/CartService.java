package com.shoyab.petstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shoyab.petstore.DTO.CartRequestDto;
import com.shoyab.petstore.DTO.CartResponseDto;
import com.shoyab.petstore.model.Cart;
import com.shoyab.petstore.model.CartItem;
import com.shoyab.petstore.model.Product;
import com.shoyab.petstore.model.Users;
import com.shoyab.petstore.repository.CartItemRepo;
import com.shoyab.petstore.repository.CartRepo;
import com.shoyab.petstore.repository.ProductRepo;
import com.shoyab.petstore.repository.UserRepo;

@Service
public class CartService {

    private final CartRepo cartRepo;
    private final CartItemRepo cartItemRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    public CartService(CartRepo cartRepo,
                       CartItemRepo cartItemRepo,
                       UserRepo userRepo,
                       ProductRepo productRepo) {

        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    // Add Product To Cart
    public CartResponseDto addcarts(CartRequestDto dto) {

        Users user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        Cart cart = cartRepo.findByUser(user).orElse(null);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepo.save(cart);
        }

        Optional<CartItem> optionalItem =
                cartItemRepo.findByCartAndProduct(cart, product);

        CartItem item;

        if (optionalItem.isPresent()) {

            item = optionalItem.get();
            item.setQuantity(item.getQuantity() + dto.getQuantity());

        } else {

            item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(dto.getQuantity());

        }

        cartItemRepo.save(item);

        return convertToDto(item);
    }
    public CartResponseDto updateQuantity(int cartItemId, int quantity) {
        CartItem item = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart Item Not Found"));
        item.setQuantity(quantity);
        cartItemRepo.save(item);
        return convertToDto(item);
    }

    // Get Cart By User
    public List<CartResponseDto> findid(int userId) {

        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Cart cart = cartRepo.findByUser(user).orElse(null);
        if (cart == null) {
            return new ArrayList<>(); // no cart yet = empty cart, not an error
        }

        List<CartItem> items = cartItemRepo.findByCart(cart);

        List<CartResponseDto> response = new ArrayList<>();

        for (CartItem item : items) {
            response.add(convertToDto(item));
        }

        return response;
    }

    // Delete Cart Item
    public void deletecarts(int cartItemId) {

        cartItemRepo.deleteById(cartItemId);

    }

    // DTO Converter
    private CartResponseDto convertToDto(CartItem item) {

        Product product = item.getProduct();

        CartResponseDto dto = new CartResponseDto();

        dto.setCartItemId(item.getCartItemId());
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setImageUrl(product.getImageUrl());
        dto.setPrice(product.getPrice());
        dto.setDiscountPrice(product.getDiscountPrice());
        dto.setCategoryName(product.getCategory().getCategoryName());
        dto.setQuantity(item.getQuantity());

        return dto;
    }
}