package com.rimba.technicaltest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rimba.technicaltest.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
    
}
