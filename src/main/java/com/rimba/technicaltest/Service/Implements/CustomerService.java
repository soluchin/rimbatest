package com.rimba.technicaltest.Service.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimba.technicaltest.Entity.Customer;
import com.rimba.technicaltest.Repository.CustomerRepo;
import com.rimba.technicaltest.Service.ICustomerService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private CustomerRepo _customerRepo;

    public Customer getById(Integer id) throws EntityNotFoundException{
        return _customerRepo.findById(id).get();
    }

    @Override
    public Customer create(Customer customer) {
        return _customerRepo.save(customer);
    }
}
