package com.rimba.technicaltest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rimba.technicaltest.Entity.TransactionItem;
import java.util.List;


public interface TransactionItemRepo extends JpaRepository<TransactionItem, Integer>{
    List<TransactionItem> findByTransactionId(Integer transactionId);
}
