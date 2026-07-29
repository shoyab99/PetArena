package com.shoyab.petstore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DashboardDto {
	 private long totalUsers;
	    private long totalProducts;
	    private long totalOrders;
	    private long totalCategories;
	    private double revenue;
	   
}
