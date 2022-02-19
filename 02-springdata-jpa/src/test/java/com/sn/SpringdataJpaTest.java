package com.sn;

import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Customer;
import com.sn.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Optional;


@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class  SpringdataJpaTest {
    @Autowired
    CustomerRepository repository;

    @Test
    public void testC(){
        Optional<Customer> byId = repository.findById(1L);
        System.out.println(byId.get());
    }
    @Test
    public void testU(){
        Customer customer = new Customer();
        customer.setCustId(9L);
        customer.setCustName("asd");
        repository.save(customer);
    }
    @Test
    public void testD(){
        Customer customer = new Customer();
        customer.setCustId(9L);
        customer.setCustName("asd");
        repository.delete(customer);
    }
    @Test
    public void testFindAll(){
        Iterable<Customer> all = repository.findAllById(Arrays.asList(1L, 5L, 8L));
        System.out.println(all);
    }
    @Test
    public void test(){
        Iterable<Customer> all = repository.findAllById(Arrays.asList(1L, 5L, 8L));
        System.out.println(all);
    }
}
