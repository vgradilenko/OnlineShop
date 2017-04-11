package com.grava.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity(name = "consumers")
public class Consumer implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "telephone", nullable = false)
    private int telephone;

    @Column
    private double money;

    @OneToMany(mappedBy = "consumer")
    private Set<Order> orders;
}
