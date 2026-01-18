package com.infosys.wmat.repository; // Change to your package

import com.infosys.wmat.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Find products by category (useful for filtering later)
    List<Product> findByCategory(String category);
}