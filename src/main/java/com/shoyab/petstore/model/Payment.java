package com.shoyab.petstore.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	private double amount;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	private String TranscationId;
	private LocalDateTime paymentDate;
	@OneToOne
	@JoinColumn(name = "order_id")
	@JsonBackReference
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Orders order;
	
}
