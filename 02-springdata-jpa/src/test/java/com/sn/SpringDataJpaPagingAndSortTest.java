package com.sn;

import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Customer;
import com.sn.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringDataJpaPagingAndSortTest {
    @Autowired
    CustomerRepository repository;

    @Test
    public void testPaging(){
        Page<Customer> all = repository.findAll(PageRequest.of(0, 2));
        System.out.println(all.getTotalPages());
        System.out.println(all.getTotalElements());
        System.out.println(all.getContent());
    }
    @Test
    public void testSort(){
        Sort sort = Sort.by("custId").descending();
        Iterable<Customer> all = repository.findAll(sort);
        System.out.println(all);

    }
    @Test
    public void testPagingAndSort(){
        Sort.TypedSort<Customer> sortType = Sort.sort(Customer.class);
        Sort sort = sortType.by(Customer::getCustId).ascending();
        // Sort sort = Sort.by("custId").ascending();
        Page<Customer> all = repository.findAll(PageRequest.of(0, 2, sort));
        System.out.println(all.getContent());
        System.out.println(all.getTotalPages());
        System.out.println(all.getTotalElements());

    }
}
