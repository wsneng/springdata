package com.sn.springdata.rest;

import com.sn.springdata.pojo.BaseInfo;
import com.sn.springdata.pojo.Customer;
import com.sn.springdata.repositories.BaseInfoRepository;
import com.sn.springdata.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customer/all")
    public Iterable<Customer> getAll(){
        return customerService.getAll();
    }


}
