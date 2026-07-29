package com.shoyab.petstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int addressId;
	private String fullName;
	@Column(length = 10)
	@Pattern(regexp = "\\d{10}")
	private String phoneNumber;
	private String houseNo;
	private String street;
	@NotBlank
	private String city;
	@NotBlank
	private String state;
	@Column(nullable = false)
	@NotBlank
	private String pincode;
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonBackReference
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Users users;
	
	
}
