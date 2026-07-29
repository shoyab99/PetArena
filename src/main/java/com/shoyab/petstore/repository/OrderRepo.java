package com.shoyab.petstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoyab.petstore.model.Orders;
@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {

	List<Orders> findByUserUserId(int userId);
	@Query("""
			SELECT COALESCE(SUM(o.totalAmount),0)
			FROM Orders o
			WHERE o.payment.status = com.shoyab.petstore.model.PaymentStatus.SUCCESS
			""")
			double getTotalRevenue();

}
