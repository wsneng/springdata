package com.sn.springdata.service;

import com.sn.springdata.pojo.Customer;

public interface CustomerService {
    Iterable<Customer> getAll();

    Customer addCustomer(Customer customer);
}
