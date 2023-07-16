package com.rimba.technicaltest.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Product {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private Integer qty;
    private String qtyType;
    private Double price;
    private String imageLink;
}
