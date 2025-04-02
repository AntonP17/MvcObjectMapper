package com.example.mvcobjectmapper.controllers;
import com.example.mvcobjectmapper.model.Product;
import com.example.mvcobjectmapper.service.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts() throws IOException {
        List<Product> products = productService.getAllProducts();
        return objectMapper.writeValueAsString(products);
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id) throws IOException {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
        return objectMapper.writeValueAsString(product);
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody String productJson) throws IOException {
        Product product = objectMapper.readValue(productJson, Product.class);
        Product createdProduct = productService.createProduct(product);
        return objectMapper.writeValueAsString(createdProduct);
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @RequestBody String productJson) throws IOException {
        Product productDetails = objectMapper.readValue(productJson, Product.class);
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return objectMapper.writeValueAsString(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
