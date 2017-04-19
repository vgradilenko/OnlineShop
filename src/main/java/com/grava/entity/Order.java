package com.grava.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity(name = "orders")
@DynamicUpdate(true)
@DynamicInsert(true)
@Data
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "order_date")
    private LocalDate date;

    @Column(name = "sum")
    private double sum;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<Product> products;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "consumer_id", nullable = false)
    private Consumer consumer;
}
