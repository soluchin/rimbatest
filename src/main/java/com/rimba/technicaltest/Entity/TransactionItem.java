package com.rimba.technicaltest.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TransactionItem {
    @Id @GeneratedValue
    private Integer id;
    private Integer transactionId;
    private Integer itemId;
    private Integer qty;
    private Double price;
}
