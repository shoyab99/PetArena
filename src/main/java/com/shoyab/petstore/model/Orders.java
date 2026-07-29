package com.shoyab.petstore.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	private LocalDateTime orderDate;
	private double totalAmount;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	@OneToMany(mappedBy = "order",cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<OrderItem> orderitems;
	@OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
	@JsonManagedReference
	private Payment payment;

}
