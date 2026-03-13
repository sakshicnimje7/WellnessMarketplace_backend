package com.infosys.wmat.service;

import com.infosys.wmat.entity.Order;
import com.infosys.wmat.entity.Product;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.OrderRepository;
import com.infosys.wmat.repository.ProductRepository;
import com.infosys.wmat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;

    // Fixed Method: Uses correct Entity fields
    public Order placeOrder(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CONFIRMED");

        // FIX 1: Use the correct setter name we defined in Order.java
        // Old Error: order.setTotalPrice(...)
        double total = product.getPrice() * quantity;
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    // Fixed Method: Uses correct Repository method
    public List<Order> getUserOrders(Long userId) {
        // FIX 2: Fetch the User object first
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Then use findByUser (which we added to the Repository)
        // Old Error: orderRepository.findByUserId(userId)
        return orderRepository.findByUser(user);
    }
}