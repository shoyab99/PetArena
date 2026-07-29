	package com.shoyab.petstore.model;
	
	import java.math.BigDecimal;
	import java.time.LocalDateTime;
	import java.util.List;
	
	import org.hibernate.annotations.CreationTimestamp;
	import org.hibernate.annotations.UpdateTimestamp;
	
	import com.fasterxml.jackson.annotation.JsonBackReference;
	import com.fasterxml.jackson.annotation.JsonIgnore;
	
	import jakarta.persistence.CascadeType;
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.OneToMany;
	import lombok.Data;
	import lombok.EqualsAndHashCode;
	import lombok.NoArgsConstructor;
	import lombok.ToString;
	
	@Entity
	@Data
	@NoArgsConstructor
	public class Product {
	   
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int productId;
		@Column(nullable = false)
		private String productName;
		@Column(length = 1000)
		private String description;
		@Column(nullable = false)
		private double price;
		private Double discountPrice;
		private int stock;
		private String brand;
		private String imageUrl;
		private String badge;
		@CreationTimestamp
		private LocalDateTime createdAt;
		@UpdateTimestamp
		private LocalDateTime updatedAt;
		@ManyToOne
		@JoinColumn(name="category_id")
		@JsonBackReference
		@ToString.Exclude
		@EqualsAndHashCode.Exclude
		private Category category;
		@OneToMany(mappedBy = "product")
		@JsonIgnore
		@ToString.Exclude
		@EqualsAndHashCode.Exclude
		private List<CartItem> cartItems;
		@OneToMany(mappedBy = "product")
		@JsonIgnore
		@ToString.Exclude
		@EqualsAndHashCode.Exclude
		private List<OrderItem> orderItems;
		@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
		@JsonIgnore
		@ToString.Exclude
		@EqualsAndHashCode.Exclude
		private List<Review> reviews;
		@Column(nullable = false)
		private boolean active = true;
	}
