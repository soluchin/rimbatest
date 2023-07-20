package com.rimba.technicaltest.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String discountType;
    private Double discount;
}
