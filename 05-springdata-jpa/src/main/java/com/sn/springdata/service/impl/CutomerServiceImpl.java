package com.sn.springdata.service.impl;

import com.sn.springdata.pojo.Customer;
import com.sn.springdata.repositories.CustomerRepository;
import com.sn.springdata.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CutomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository repository;
    @Override
    public Iterable<Customer> getAll() {
        return repository.findAll();
    }
}
