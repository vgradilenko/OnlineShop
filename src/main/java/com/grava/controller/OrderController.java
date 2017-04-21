package com.grava.controller;

import com.grava.entity.Consumer;
import com.grava.entity.Order;
import com.grava.entity.Product;
import com.grava.repository.ConsumerRepository;
import com.grava.repository.OrderRepository;
import com.grava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/order/")
public class OrderController {

    private OrderRepository orderRepository;
    private ConsumerRepository consumerRepository;
    private ProductRepository productRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository,
                           ConsumerRepository consumerRepository,
                           ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.consumerRepository = consumerRepository;
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/{id}")
    public Order getInfoById(@PathVariable long id) {
        return orderRepository.findOne(id);
    }

    @GetMapping(value = "/all")
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    // FIXME: 4/20/2017 It's shit
    @PostMapping(value = "/save")
    public void createNewOrder(@RequestBody Order order) {
        System.out.println(order);
        order.setConsumer(consumerRepository.findOne(order.getConsumer().getId()));
        order.setDate(LocalDate.now());
        order.setProducts(order.getProducts());
        orderRepository.saveAndFlush(order);
    }
}
