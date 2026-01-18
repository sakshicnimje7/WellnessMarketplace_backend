package com.infosys.wmat.config;

import com.infosys.wmat.entity.Product;
import com.infosys.wmat.repository.OrderRepository; // <--- Import this
import com.infosys.wmat.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    // Inject BOTH repositories here
    @Bean
    public CommandLineRunner loadData(ProductRepository productRepository, OrderRepository orderRepository) {
        return args -> {
            // 1. CLEAR ORDERS FIRST
            // We must delete orders before products to satisfy the Foreign Key Constraint
            orderRepository.deleteAll();
            System.out.println("✅ Old Orders Cleared");

            // 2. CLEAR PRODUCTS SECOND
            productRepository.deleteAll();
            System.out.println("✅ Old Products Cleared");

            // 3. ADD FRESH PRODUCTS WITH IMAGES
            Product p1 = new Product();
            p1.setName("Bamboo Yoga Mat");
            p1.setCategory("Equipment");
            p1.setPrice(45.99);
            p1.setDescription("Eco-friendly non-slip yoga mat.");
            p1.setImageUrl("https://images.unsplash.com/photo-1592432678016-e910b452f9a2?auto=format&fit=crop&w=500&q=60");
            productRepository.save(p1);

            Product p2 = new Product();
            p2.setName("Herbal Tea");
            p2.setCategory("Nutrition");
            p2.setPrice(12.00);
            p2.setDescription("Calming organic tea blend.");
            p2.setImageUrl("https://images.unsplash.com/photo-1597481499750-3e6b22637e12?auto=format&fit=crop&w=500&q=60");
            productRepository.save(p2);

            Product p3 = new Product();
            p3.setName("Meditation Cushion");
            p3.setCategory("Comfort");
            p3.setPrice(29.50);
            p3.setDescription("Ergonomic cushion for deep focus.");
            p3.setImageUrl("https://images.unsplash.com/photo-1584132967334-10e028bd69f7?auto=format&fit=crop&w=500&q=60");
            productRepository.save(p3);

            System.out.println("✅ Products Refreshed with Images!");
        };
    }
}