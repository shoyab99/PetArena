package com.shoyab.petstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shoyab.petstore.DTO.ProductRequestDto;
import com.shoyab.petstore.DTO.ProductResponseDto;
import com.shoyab.petstore.model.Category;
import com.shoyab.petstore.model.Product;
import com.shoyab.petstore.repository.CategoryRepo;
import com.shoyab.petstore.repository.ProductRepo;

@Service
public class ProductService {
private ProductRepo repo;


private CategoryRepo categoryRepo;

public ProductService(ProductRepo repo, CategoryRepo categoryRepo) {
    this.repo = repo;
    this.categoryRepo = categoryRepo;
}
public Product getid(int id) {
	// TODO Auto-generated method stub
	return repo.findById(id).orElse(null);
}
public Page<ProductResponseDto> getAllProducts(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);

    Page<Product> products = repo.findByActiveTrue(pageable);

    return products.map(product -> {

        ProductResponseDto dto = new ProductResponseDto();

        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setDiscountPrice(product.getDiscountPrice());
        dto.setStock(product.getStock());
        dto.setBrand(product.getBrand());
        dto.setImageUrl(product.getImageUrl());
        dto.setCategoryName(product.getCategory().getCategoryName());
        

        return dto;
    });
}

public List<ProductResponseDto> getLatestProducts() {

    Pageable pageable = PageRequest.of(0, 10);

    List<Product> products =
    		repo.findByActiveTrueOrderByProductIdDesc(pageable);
    

    List<ProductResponseDto> response = new ArrayList<>();

    for(Product product : products){

        ProductResponseDto dto = new ProductResponseDto();

        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setDiscountPrice(product.getDiscountPrice());
        dto.setImageUrl(product.getImageUrl());
        dto.setCategoryName(product.getCategory().getCategoryName());
        dto.setCategoryId(product.getCategory().getCategoryId());

        response.add(dto);
    }

    return response;
}

public ProductResponseDto addProduct(ProductRequestDto dto) {

    Product product = new Product();

    product.setProductName(dto.getProductName());
    product.setDescription(dto.getDescription());
    product.setPrice(dto.getPrice());
    product.setDiscountPrice(dto.getDiscountPrice()); // if you have this
    product.setStock(dto.getStock());
    product.setBrand(dto.getBrand());                 // if you have this
    product.setImageUrl(dto.getImageUrl());

    Category category = categoryRepo.findById(dto.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));

    product.setCategory(category);

    Product saved = repo.save(product);

    ProductResponseDto response = new ProductResponseDto();

    response.setProductId(saved.getProductId());
    response.setProductName(saved.getProductName());
    response.setDescription(saved.getDescription());
    response.setPrice(saved.getPrice());
    response.setDiscountPrice(saved.getDiscountPrice()); // if present
    response.setStock(saved.getStock());
    response.setBrand(saved.getBrand());                 // if present
    response.setImageUrl(saved.getImageUrl());           // <-- Important
    response.setCategoryName(saved.getCategory().getCategoryName());

    return response;
}

public void deleteid(int id) {

    Product product = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

//    product.setActive(false);

    repo.delete(product);
}

public List<Product> search(String name) {
	
	return repo.findByProductNameContainingIgnoreCaseAndActiveTrue(name);
}
//public List<Product> highest(double p){
//	return repo.findExpensiveProducts(p);
//}
public List<Product> ascending(){
	return repo.ascending();
}
public void deleteall() {
	// TODO Auto-generated method stub
	repo.deleteAll();
	
}
private ProductResponseDto convertToDto(Product product) {

    ProductResponseDto dto = new ProductResponseDto();

    dto.setProductId(product.getProductId());
    dto.setProductName(product.getProductName());
    dto.setDescription(product.getDescription());
    dto.setPrice(product.getPrice());
    dto.setDiscountPrice(product.getDiscountPrice());
    dto.setStock(product.getStock());
    dto.setBrand(product.getBrand());
    dto.setImageUrl(product.getImageUrl());
    dto.setCategoryName(product.getCategory().getCategoryName());
    dto.setCategoryId(product.getCategory().getCategoryId());

    return dto;
}
public List<ProductResponseDto> getAllProducts() {

    return repo.findByActiveTrue()
            .stream()
            .map(product -> convertToDto(product))
            .toList();
}
public ProductResponseDto updateProduct(int id, ProductRequestDto dto) {

    Product product = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

    product.setProductName(dto.getProductName());
    product.setDescription(dto.getDescription());
    product.setPrice(dto.getPrice());
    product.setDiscountPrice(dto.getDiscountPrice());
    product.setStock(dto.getStock());
    product.setBrand(dto.getBrand());
    product.setImageUrl(dto.getImageUrl());
    product.setBadge(dto.getBadge());

    Category category = categoryRepo.findById(dto.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));

    product.setCategory(category);

    Product updatedProduct = repo.save(product);

    return convertToDto(updatedProduct);
}
}
