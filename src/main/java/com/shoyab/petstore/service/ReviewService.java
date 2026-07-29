package com.shoyab.petstore.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shoyab.petstore.DTO.ReviewRequestDto;
import com.shoyab.petstore.DTO.ReviewResponseDto;
import com.shoyab.petstore.model.Product;
import com.shoyab.petstore.model.Review;
import com.shoyab.petstore.model.Users;
import com.shoyab.petstore.repository.ProductRepo;
import com.shoyab.petstore.repository.ReviewRepo;
import com.shoyab.petstore.repository.UserRepo;

@Service
public class ReviewService {
	private ReviewRepo repo;
	private UserRepo userRepo;
	private ProductRepo productRepo;



public ReviewService(ReviewRepo repo, UserRepo userRepo, ProductRepo productRepo) {
		super();
		this.repo = repo;
		this.userRepo = userRepo;
		this.productRepo = productRepo;
	}

public List<Review> getall() {
	// TODO Auto-generated method stub
	return repo.findAll();
}

public Review getid(int id) {
	// TODO Auto-generated method stub
	return repo.findById(id).orElse(null);
}
public List<ReviewResponseDto> getReviewsByProduct(int productId) {

    List<Review> reviews = repo.findByProductProductId(productId);

    return reviews.stream().map(r -> {

        ReviewResponseDto dto = new ReviewResponseDto();

        dto.setReviewId(r.getReviewId());
        dto.setUserName(
                r.getUser().getFirstName() + " " +
                r.getUser().getLastName());
        dto.setRating(r.getRating());
        dto.setComment(r.getComment());
        dto.setReviewDate(r.getReviewDate());

        return dto;

    }).toList();
}
public ReviewResponseDto addReview(ReviewRequestDto dto) {

    Users user = userRepo.findById(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("User Not Found"));

    Product product = productRepo.findById(dto.getProductId())
            .orElseThrow(() -> new RuntimeException("Product Not Found"));

    Review review = new Review();

    review.setUser(user);
    review.setProduct(product);
    review.setRating(dto.getRating());
    review.setComment(dto.getComment());
    review.setReviewDate(LocalDateTime.now());

    review = repo.save(review);

    ReviewResponseDto response = new ReviewResponseDto();

    response.setReviewId(review.getReviewId());
    response.setUserName(
            user.getFirstName() + " " + user.getLastName());
    response.setRating(review.getRating());
    response.setComment(review.getComment());
    response.setReviewDate(review.getReviewDate());

    return response;
}

public void deleteid(int id) {
	// TODO Auto-generated method stub
	repo.deleteById(id);
}

}
