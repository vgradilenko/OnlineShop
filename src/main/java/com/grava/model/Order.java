package com.grava.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Document
@Data
public class Order implements Serializable {

    @Id
    @ApiModelProperty(notes = "Order id")
    private String id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(notes = "Order date")
    @JsonIgnore
    private LocalDate date;

    @DBRef
    @ApiModelProperty(notes = "List products in order")
    private List<Product> products;

    @DBRef
    @ApiModelProperty(notes = "Consumer")
    private Consumer consumer;
}
