package com.shoyab.petstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoyab.petstore.model.Orders;
import com.shoyab.petstore.model.Payment;
@Repository

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

	Payment findByOrder(Orders order);

}
