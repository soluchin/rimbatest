package com.rimba.technicaltest.Entity.Model.ResponseModel;

import java.util.List;

import com.rimba.technicaltest.Entity.Transaction;
import com.rimba.technicaltest.Entity.TransactionItem;

import lombok.Data;

@Data
public class TransactionResponseModel {
    Transaction transaction;
    List<TransactionItem> transactionItem;
}
