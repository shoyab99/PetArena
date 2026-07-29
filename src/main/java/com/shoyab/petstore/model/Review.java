package com.shoyab.petstore.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;
	private int rating;
	private String comment;
	@CreationTimestamp
	private LocalDateTime reviewDate;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

}
