package com.shoyab.petstore.service;



import org.springframework.stereotype.Service;

import com.shoyab.petstore.DTO.DashboardDto;
import com.shoyab.petstore.model.Orders;
import com.shoyab.petstore.model.PaymentStatus;
import com.shoyab.petstore.repository.CategoryRepo;
import com.shoyab.petstore.repository.OrderRepo;
import com.shoyab.petstore.repository.ProductRepo;
import com.shoyab.petstore.repository.UserRepo;

@Service
public class Adminservice {

    private UserRepo userRepo;
    private ProductRepo productRepo;
    private OrderRepo orderRepo;
    private CategoryRepo catrepo;

    public Adminservice(UserRepo userRepo,
                        ProductRepo productRepo,
                        OrderRepo orderRepo,CategoryRepo catrepo) {

        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.catrepo=catrepo;
    }

    public DashboardDto getDashboard() {

        long totalUsers = userRepo.count();

        long totalProducts = productRepo.count();

        long totalOrders = orderRepo.count();

        long totalCategories = catrepo.count();

        double revenue = orderRepo.getTotalRevenue();

        return new DashboardDto(
                totalUsers,
                totalProducts,
                totalOrders,
                totalCategories,
                revenue
        );
    }

}