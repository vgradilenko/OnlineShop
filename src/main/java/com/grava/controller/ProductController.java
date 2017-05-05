package com.grava.controller;

import com.grava.model.Product;
import com.grava.repository.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/product/")
@Api(value = "onlineshop", description = "CRUD operations with products")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ApiOperation(value = "View a list of available products",response = Collection.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    @GetMapping(value = "/all")
    public Collection<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable String id) {
        return productRepository.findOne(id);
    }

    @DeleteMapping (value = "/delete/{id}")
    public void deleteProduct(@PathVariable String id) {
        productRepository.delete(id);
    }

    @PostMapping(value = "/save")
    public void saveProduct(@RequestBody Product product) {
        product.setDate(LocalDate.now());
        productRepository.save(product);
    }
}
