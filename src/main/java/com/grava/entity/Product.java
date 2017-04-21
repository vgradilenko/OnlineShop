package com.grava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "product_name", nullable = false, length = 30)
    private String name;

    @Column(name = "product_price", nullable = false)
    private double price;

    @JsonIgnore
    @Column(name = "product_date", nullable = false)
    private LocalDate date;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Order> order;
}
