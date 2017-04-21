package com.grava.controller;

import com.grava.entity.Order;
import com.grava.entity.Product;
import com.grava.repository.ConsumerRepository;
import com.grava.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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
        order.setConsumer(consumerRepository.findOne(order.getConsumer().getId()));
        if (!checkMoney(order)){
            return;
        }
        order.setDate(LocalDate.now());
        orderRepository.saveAndFlush(order);
    }

    // FIXME: 4/21/2017 fix this
    private boolean checkMoney(Order order){
        int summa = 0;
        for (Product price: order.getProducts()) {
            summa += price.getPrice();
        }

        if (order.getConsumer().getMoney() < summa) {
            return false;
        }
        order.getConsumer().setMoney(order.getConsumer().getMoney() - summa);
        return true;
    }
}
