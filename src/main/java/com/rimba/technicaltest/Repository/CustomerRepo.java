package com.rimba.technicaltest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rimba.technicaltest.Entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    
}
