package com.rimba.technicaltest.Service;

import com.rimba.technicaltest.Entity.Customer;

public interface ICustomerService {
    Customer create(Customer customer);
    Customer getById(Integer id);
}
