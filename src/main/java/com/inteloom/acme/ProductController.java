package com.inteloom.acme;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    
    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/products")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Product> coolCars() {
        return repository.findAll().stream()
                .collect(Collectors.toList());
    }

}