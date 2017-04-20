package com.grava.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name", nullable = false, length = 30)
    private String name;

    @Column(name = "product_price", nullable = false)
    private double price;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "product_date", nullable = false)
    private LocalDate date;

    @JsonIgnore
    @ManyToOne()
    private Order order;
}
