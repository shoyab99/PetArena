package com.shoyab.petstore.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoyab.petstore.DTO.ProductRequestDto;
import com.shoyab.petstore.DTO.ProductResponseDto;
import com.shoyab.petstore.model.Product;
import com.shoyab.petstore.service.ProductService;
@CrossOrigin
@RestController
@RequestMapping("/prod")
public class ProductController {
	private ProductService service;

	public ProductController(ProductService service) {
		super();
		this.service = service;
	}
 
 @GetMapping
 public Page<ProductResponseDto> getProducts(
         @RequestParam(defaultValue = "0") int page,
         @RequestParam(defaultValue = "12") int size) {

     return service.getAllProducts(page, size);
 }
 @GetMapping("/latest")
 public List<ProductResponseDto> latestProducts() {
     return service.getLatestProducts();
 }
 @GetMapping("/{id}")
 public Product getid(@PathVariable int id) {
	 return service.getid(id);
 }
 @GetMapping("/all")
 public List<ProductResponseDto> getAllProducts() {
     return service.getAllProducts();
 }
 @PostMapping
 public ProductResponseDto add(@RequestBody ProductRequestDto prod) {
	 return  service.addProduct(prod);
 }
 @DeleteMapping("/{id}")
 public void deleteid(@PathVariable int id) {
	 service.deleteid(id);
 }
 @GetMapping("/search/{name}")
 public List<Product> searchProduct(
         @PathVariable String name){

     return service.search(name);
 }
 @GetMapping("/prod/price")
 public List<Product> ascend(){
	 return service.ascending();
	 }
 @DeleteMapping("/id/all")
 public void deleteall() {
	 service.deleteall();
 }
 @PutMapping("/{id}")
 public ProductResponseDto update(
         @PathVariable int id,
         @RequestBody ProductRequestDto dto){

     return service.updateProduct(id, dto);
 }
}
