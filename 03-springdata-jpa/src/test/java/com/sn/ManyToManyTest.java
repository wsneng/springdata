package com.sn;

import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Customer;
import com.sn.pojo.Message;
import com.sn.pojo.Role;
import com.sn.repositories.CustomerRepository;
import com.sn.repositories.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ManyToManyTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    public void testR02(){
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("超级管理员"));
        roles.add(new Role("商品管理员"));

        Customer customer = new Customer();
        customer.setCustName("周瑜");
        customer.setRoles(roles);

        repository.save(customer);
    }

}
