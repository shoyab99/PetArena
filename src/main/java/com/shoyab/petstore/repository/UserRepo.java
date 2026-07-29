package com.shoyab.petstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoyab.petstore.model.Users;
@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{
	Optional<Users> findByEmailOrPhoneNumber(String email, String phoneNumber);
}
