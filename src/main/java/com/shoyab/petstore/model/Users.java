package com.shoyab.petstore.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
//import org.springframework.data.annotation.CreatedDate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(nullable = false)
	@NotBlank
	private String firstName;
	private String lastName;
	@Column(nullable = false,unique = true)
	@Email(message = "enter a valid email")
	private String email;
	@Column(length = 10)
	@Pattern(regexp = "\\d{10}")
	private String phoneNumber;
	@Size(min = 6,message = "minimum 6 charecters are required")
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role=Role.USER;
	private boolean isVerified=false;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	@OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
	@JsonManagedReference
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Address> addressess;
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonManagedReference
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Cart cart;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Orders> orders;
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Review> reviews;
	
}
