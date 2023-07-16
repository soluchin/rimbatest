package com.rimba.technicaltest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rimba.technicaltest.Entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer>{
    
}
