package com.shoyab.petstore.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@OneToOne
	@JoinColumn(name="user_id")
	@JsonBackReference
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Users user;
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
	@JsonManagedReference
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<CartItem> items;
	

}
