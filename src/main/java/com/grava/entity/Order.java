package com.grava.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "orders")
@Data
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "order_date")
    private LocalDate date;

    @ManyToMany
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "consumer_id", nullable = false)
    private Consumer consumer;
}
