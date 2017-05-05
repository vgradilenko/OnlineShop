package com.grava.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Set;

@Data
@Document
public class Consumer implements Serializable {

    @Id
    @ApiModelProperty(notes = "Consumer name")
    private String id;
    @ApiModelProperty(notes = "Consumer first name")
    private String firstName;
    @ApiModelProperty(notes = "Consumer last name")
    private String lastName;
    @ApiModelProperty(notes = "Consumer telephone")
    private int telephone;
    @ApiModelProperty(notes = "Consumer money")
    private double money;

    @DBRef
    @ApiModelProperty(notes = "Consumer orders")
    private Set<Order> orders;
}
