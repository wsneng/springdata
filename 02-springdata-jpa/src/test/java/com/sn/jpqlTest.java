package com.sn;

import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Customer;
import com.sn.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class jpqlTest {
    @Autowired
    CustomerRepository repository;
    @Test
    public void testR(){
        List<Customer> customer = repository.findCustomerByCustName("ww");
        System.out.println(customer);
    }
    @Test
    public void testU(){
        int result = repository.updateCustomer("ws", 7L);
        System.out.println(result);
    }
    @Test
    public void testD(){
        int result = repository.deleteCustomer("ws");
        System.out.println(result);
    }
    @Test
    public void testC(){
        int result = repository.insertCustomer(6L);
        System.out.println(result);
    }
    @Test
    public void testC_SQL(){
        List<Customer> customer = repository.findBySQL("wc");
        System.out.println(customer);
    }

}
