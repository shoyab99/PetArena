package com.shoyab.petstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shoyab.petstore.DTO.AdminOrderDto;
import com.shoyab.petstore.DTO.OrderDetailsDto;
import com.shoyab.petstore.DTO.OrderRequestDto;
import com.shoyab.petstore.DTO.OrderResponseDto;
import com.shoyab.petstore.model.Address;
import com.shoyab.petstore.model.Cart;
import com.shoyab.petstore.model.CartItem;
import com.shoyab.petstore.model.OrderItem;
import com.shoyab.petstore.model.OrderStatus;
import com.shoyab.petstore.model.Orders;
import com.shoyab.petstore.model.Payment;
import com.shoyab.petstore.model.PaymentStatus;
import com.shoyab.petstore.model.Product;
import com.shoyab.petstore.model.Users;
import com.shoyab.petstore.repository.AddressRepo;
import com.shoyab.petstore.repository.CartItemRepo;
import com.shoyab.petstore.repository.CartRepo;
import com.shoyab.petstore.repository.OrderItemRepo;
import com.shoyab.petstore.repository.OrderRepo;
import com.shoyab.petstore.repository.PaymentRepo;
import com.shoyab.petstore.repository.UserRepo;

@Service
public class OrderService {
	private OrderRepo orderRepo;
	private UserRepo userRepo;
	private AddressRepo addressRepo;
	private CartRepo cartRepo;
	private CartItemRepo cartItemRepo;
	private OrderItemRepo orderItemRepo;
	private PaymentRepo paymentRepo;

	

	public OrderService(OrderRepo orderRepo, UserRepo userRepo, AddressRepo addressRepo, CartRepo cartRepo,
			CartItemRepo cartItemRepo, OrderItemRepo orderItemRepo, PaymentRepo paymentRepo) {
		super();
		this.orderRepo = orderRepo;
		this.userRepo = userRepo;
		this.addressRepo = addressRepo;
		this.cartRepo = cartRepo;
		this.cartItemRepo = cartItemRepo;
		this.orderItemRepo = orderItemRepo;
		this.paymentRepo = paymentRepo;
	}

	public List<Orders> getall() {
	    return orderRepo.findAll();
	}

	public OrderDetailsDto getOrderDetails(int id) {

	    Orders order = orderRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Order Not Found"));

	    Payment payment = paymentRepo.findByOrder(order);

	    OrderDetailsDto dto = new OrderDetailsDto();

	    dto.setOrderId(order.getOrderId());
	    dto.setTotalAmount(order.getTotalAmount());
	    dto.setOrderStatus(order.getStatus().name());
	    dto.setOrderDate(order.getOrderDate());

	    dto.setAddress(order.getAddress());

	    dto.setOrderitems(order.getOrderitems());

	    dto.setPaymentStatus(payment.getStatus().name());

	    return dto;
	}
	public void deleteid(int id) {
	    orderRepo.deleteById(id);
	}
	public OrderResponseDto placeOrder(OrderRequestDto dto) {
		
		
	    Users user = userRepo.findById(dto.getUserId())
	            .orElseThrow(() -> new RuntimeException("User Not Found"));

	    Address address = addressRepo.findById(dto.getAddressId())
	            .orElseThrow(() -> new RuntimeException("Address Not Found"));

	    Cart cart = cartRepo.findByUser(user)
	            .orElseThrow(() -> new RuntimeException("Cart Not Found"));

	    List<CartItem> cartItems = cartItemRepo.findByCart(cart);

	    if (cartItems.isEmpty()) {
	        throw new RuntimeException("Cart is Empty");
	    }

	    double totalAmount = 0;

	    for (CartItem item : cartItems) {
	        totalAmount += item.getProduct().getDiscountPrice() * item.getQuantity();
	    }

	    // Create Order
	    Orders order = new Orders();
	    order.setUser(user);
	    order.setAddress(address);      // Requires Address field in Orders
	    order.setOrderDate(LocalDateTime.now());
	    order.setStatus(OrderStatus.PENDING);
	    order.setTotalAmount(totalAmount);

	    order = orderRepo.save(order);

	    // Save Order Items
	    for (CartItem item : cartItems) {

	        OrderItem orderItem = new OrderItem();

	        orderItem.setOrder(order);
	        orderItem.setProduct(item.getProduct());
	        orderItem.setQuantity(item.getQuantity());
	        orderItem.setPrice(item.getProduct().getDiscountPrice());

	        orderItemRepo.save(orderItem);
	    }

	    // Create Payment
	    Payment payment = new Payment();

	    payment.setOrder(order);
	    payment.setAmount(totalAmount);
	    payment.setPaymentMethod(dto.getPaymentMethod());
	    payment.setStatus(PaymentStatus.PENDING);
	    payment.setPaymentDate(LocalDateTime.now());

	    paymentRepo.save(payment);

	    // Empty Cart
	    cartItemRepo.deleteAll(cartItems);

	    // Response DTO
	    OrderResponseDto response = new OrderResponseDto();

	    response.setOrderId(order.getOrderId());
	    response.setTotalAmount(totalAmount);
	    response.setOrderStatus(order.getStatus().name());
	    response.setPaymentStatus(payment.getStatus().name());
	    response.setMessage("Order Placed Successfully");

	    return response;
	}

	public List<OrderResponseDto> getOrdersByUser(int userId) {

	    List<Orders> orders = orderRepo.findByUserUserId(userId);

	    List<OrderResponseDto> list = new ArrayList<>();

	    for (Orders order : orders) {

	        OrderResponseDto dto = new OrderResponseDto();

	        dto.setOrderId(order.getOrderId());
	        dto.setTotalAmount(order.getTotalAmount());
	        dto.setOrderStatus(order.getStatus().name());

	        Payment payment = paymentRepo.findByOrder(order);

	        if (payment != null) {
	            dto.setPaymentStatus(payment.getStatus().name());
	        } else {
	            dto.setPaymentStatus("NOT_PAID");
	        }
	        list.add(dto);
	    }

	    return list;
	}
	public void cancelOrder(int orderId) {

	    Orders order = orderRepo.findById(orderId)
	            .orElseThrow(() -> new RuntimeException("Order Not Found"));

	    if (order.getStatus() == OrderStatus.CANCELLED) {
	        throw new RuntimeException("Order is already cancelled");
	    }

	    if (order.getStatus() != OrderStatus.PENDING) {
	        throw new RuntimeException("Only pending orders can be cancelled");
	    }

	    order.setStatus(OrderStatus.CANCELLED);

	    orderRepo.save(order);
	
	}

	public List<AdminOrderDto> getAllOrdersForAdmin() {
		 return orderRepo.findAll()
		            .stream()
		            .map(order -> {

		                AdminOrderDto dto = new AdminOrderDto();

		                dto.setOrderId(order.getOrderId());

		                dto.setCustomer(
		                        order.getUser().getFirstName() + " " +
		                        order.getUser().getLastName()
		                );

		                dto.setTotalAmount(order.getTotalAmount());

		                dto.setStatus(order.getStatus());

		                dto.setOrderDate(order.getOrderDate());

		                if (order.getPayment() != null) {
		                    dto.setPaymentStatus(order.getPayment().getStatus());
		                }

		                return dto;

		            })
		            .toList();	
	
	}
	public void updateOrderStatus(int orderId, OrderStatus status) {

	    Orders order = orderRepo.findById(orderId)
	            .orElseThrow(() -> new RuntimeException("Order not found"));

	    // Reduce stock only once
	    if (status == OrderStatus.DELIVERED &&
	        order.getStatus() != OrderStatus.DELIVERED) {

	        for (OrderItem item : order.getOrderitems()) {

	            Product product = item.getProduct();

	            if (product.getStock() < item.getQuantity()) {
	                throw new RuntimeException(
	                        product.getProductName() + " is out of stock");
	            }

	            product.setStock(product.getStock() - item.getQuantity());
	        }
	    }

	    order.setStatus(status);

	    orderRepo.save(order);
	}

	

}
