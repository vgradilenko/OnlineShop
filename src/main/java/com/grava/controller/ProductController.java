package com.grava.controller;

import com.grava.entity.Product;
import com.grava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/product/")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable long id) {
        return productRepository.findOne(id);
    }

    @GetMapping(value = "/all")
    public Collection<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping(value = "/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productRepository.delete(id);
    }

    @PostMapping(value = "/save")
    public void saveProduct(@RequestBody Product product) {
        product.setDate(LocalDate.now());
        productRepository.saveAndFlush(product);
    }
}
