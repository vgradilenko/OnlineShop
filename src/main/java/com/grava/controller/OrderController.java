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

    @Autowired
    public OrderController(OrderRepository orderRepository,
                           ConsumerRepository consumerRepository) {
        this.orderRepository = orderRepository;
        this.consumerRepository = consumerRepository;
    }

    @GetMapping(value = "/{id}")
    public Order getInfoById(@PathVariable long id) {
        return orderRepository.findOne(id);
    }

    @GetMapping(value = "/all")
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @PostMapping(value = "/save")
    public void createNewOrder(@RequestBody Order order) {
        System.out.println(order.getProducts());
        order.setConsumer(consumerRepository.findOne(order.getConsumer().getId()));
        order.setDate(LocalDate.now());
        orderRepository.saveAndFlush(order);
    }
}
