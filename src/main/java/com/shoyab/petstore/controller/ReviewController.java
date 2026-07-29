package com.shoyab.petstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoyab.petstore.DTO.ReviewRequestDto;
import com.shoyab.petstore.DTO.ReviewResponseDto;
import com.shoyab.petstore.model.Review;
import com.shoyab.petstore.service.ReviewService;
@CrossOrigin
@RestController
@RequestMapping("/review")
public class ReviewController {
	private ReviewService service;

	public ReviewController(ReviewService service) {
		super();
		this.service = service;
	}
	@GetMapping
	public List<Review> getall(){
		return service.getall();
	}

@GetMapping("/product/{productId}")
public List<ReviewResponseDto> getReviews(
        @PathVariable int productId) {

    return service.getReviewsByProduct(productId);
}
	@PostMapping
	public ReviewResponseDto addReview(
	        @RequestBody ReviewRequestDto dto) {

	    return service.addReview(dto);
	}

	@DeleteMapping("/{id}")
	public void deleteid(@PathVariable int id) {
		service.deleteid(id);
	}

}
