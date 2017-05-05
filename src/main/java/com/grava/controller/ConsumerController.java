package com.grava.controller;

import com.grava.model.Consumer;
import com.grava.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Consumer getConsumerById(@PathVariable String id) {
        return consumerRepository.findOne(id);
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody Consumer consumer) {
        consumerRepository.save(consumer);
    }
}
