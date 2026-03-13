package com.infosys.wmat.controller;

import com.infosys.wmat.entity.Order;
import com.infosys.wmat.entity.Product;
import com.infosys.wmat.entity.User;
import com.infosys.wmat.repository.OrderRepository;
import com.infosys.wmat.repository.ProductRepository;
import com.infosys.wmat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;

    // 1. ADD TO CART (Fixing the Payload Issue)
    @PostMapping("/create")
    public ResponseEntity<?> addToCart(@RequestBody Map<String, Object> payload, Principal principal) {
        // Extract data safely
        Long productId = ((Number) payload.get("productId")).longValue();
        int quantity = ((Number) payload.get("quantity")).intValue();
        String email = principal.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setTotalAmount(product.getPrice() * quantity);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CART"); // Initial status

        orderRepository.save(order);
        return ResponseEntity.ok("Item added to cart successfully");
    }

    // 2. GET MY ORDERS
    @GetMapping("/my-orders")
    public List<Order> getMyOrders(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElse(null);
        return orderRepository.findByUser(user);
    }
}