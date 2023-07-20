package com.rimba.technicaltest.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transaction {
    @Id @GeneratedValue
    private Integer id;
    private Integer customerId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String transactionCode;
    private Date transactionDate;
    private Double totalPrice;
    private Double discount;
    private Double finalPrice;
}
