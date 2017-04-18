package com.grava.controller;

import com.grava.entity.Consumer;
import com.grava.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private ConsumerRepository consumerRepository;

    @Autowired
    public ConsumerController(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @GetMapping(value = "/all")
    public Collection<Consumer> consumers() {
        return consumerRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Consumer getConsumerById(@PathVariable long id) {
        return consumerRepository.findOne(id);
    }
}
