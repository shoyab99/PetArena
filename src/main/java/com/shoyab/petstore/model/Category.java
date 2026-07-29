package com.shoyab.petstore.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	@Column(nullable = false)
	private String categoryName;
	private String description;
	@OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Product> products;
	
	
	
}
