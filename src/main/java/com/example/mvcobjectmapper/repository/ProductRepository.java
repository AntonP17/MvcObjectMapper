package com.example.mvcobjectmapper.repository;

import com.example.mvcobjectmapper.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}