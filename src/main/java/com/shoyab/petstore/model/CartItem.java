package com.shoyab.petstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartItemId;
	@ManyToOne
	@JoinColumn(name = "cart_id")
	@JsonBackReference
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Cart cart;
	@ManyToOne
	@JoinColumn(name = "product_id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Product product;
	private int quantity;
	
}
