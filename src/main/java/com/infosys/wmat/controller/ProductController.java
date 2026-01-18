package com.infosys.wmat.controller; // Change to your package

import com.infosys.wmat.entity.Product;
import com.infosys.wmat.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // 1. Add a new product (Usually strictly for Admin, but open for your testing)
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productRepository.save(product));
    }

    // 2. Get all products (For the Store Page)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }
}