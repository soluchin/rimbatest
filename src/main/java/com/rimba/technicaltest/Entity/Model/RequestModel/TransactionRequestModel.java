package com.rimba.technicaltest.Entity.Model.RequestModel;

import java.util.List;

import com.rimba.technicaltest.Entity.Customer;
import com.rimba.technicaltest.Entity.Transaction;
import com.rimba.technicaltest.Entity.TransactionItem;

import lombok.Data;

@Data
public class TransactionRequestModel {
    Customer customer;
    Transaction transaction;
    List<TransactionItem> transactionItem;
}
