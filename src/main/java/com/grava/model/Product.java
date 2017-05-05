package com.grava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class Product implements Serializable {

    @Id
    @ApiModelProperty(notes = "The database generated product ID")
    private String id;
    @ApiModelProperty(notes = "Product name")
    private String name;
    @ApiModelProperty(notes = "Product price")
    private double price;
    @ApiModelProperty(notes = "Product date")
    @JsonIgnore
    private LocalDate date;

    @DBRef
    @ApiModelProperty(notes = "orders list")
    private List<Order> orders;


}
