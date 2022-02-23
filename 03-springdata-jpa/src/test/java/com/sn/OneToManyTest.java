package com.sn;

import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Account;
import com.sn.pojo.Customer;
import com.sn.pojo.Message;
import com.sn.repositories.AccountRepository;
import com.sn.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OneToManyTest {
    @Autowired
    CustomerRepository customerRepository;

    //插入
    @Test
    public void testC01(){
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("你好1"));
        messageList.add(new Message("在吗1？"));

        Customer customer = new Customer();
        customer.setCustName("诸葛5");
        customer.setMessages(messageList);

        customerRepository.save(customer);
    }
    @Test
    @Transactional(readOnly = true)
    public void testR01(){
        Optional<Customer> byId = customerRepository.findById(4L);
        System.out.println("==============");
        System.out.println(byId);
    }
    @Test
    public void testD01(){
        customerRepository.deleteById(3L);
    }
    @Test
    @Transactional
    @Commit
    public void testU(){
        Optional<Customer> customer = customerRepository.findById(10L);
        customer.get().setCustName("小乔");
    }



}
