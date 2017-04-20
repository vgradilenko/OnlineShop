package com.grava.controller;

import com.grava.entity.Consumer;
import com.grava.entity.Order;
import com.grava.entity.Product;
import com.grava.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/order/")
public class OrderController {

    private OrderRepository orderRepository;
//    private List<Product> products;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping(value = "/{id}")
    public Order getInfoById(@PathVariable long id) {
        return orderRepository.findOne(id);
    }

    @GetMapping(value = "/all")
    public Collection<Order> getAll() {
        return orderRepository.findAll();
    }

    @PostMapping(name = "/new")
    public void createNewOrder(@PathVariable Consumer consumer,
                               @PathVariable List<Product> products) {
        Order order = new Order();
        order.setConsumer(consumer);
        order.setDate(LocalDate.now());
        order.setProducts(products);
        orderRepository.saveAndFlush(order);
    }


}
