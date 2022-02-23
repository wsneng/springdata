package com.sn;

import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Customer;
import com.sn.pojo.Message;
import com.sn.repositories.CustomerRepository;
import com.sn.repositories.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ManyToOneTest {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    // 多对一 插入
    // 得出：当插入“多”的数据的时候，使用多对一的关联关系式更加合理
    @Test
    public void testC01() {
        Customer customer = new Customer();
        customer.setCustName("司马懿");

        List<Message> list = new ArrayList<>();
        list.add(new Message("你好", customer));
        list.add(new Message("在吗", customer));

        repository.saveAll(list);
    }

    // 多对一 根据客户id查询对应的所有信息  在一对多查询最合理
    // 通过“一”进行条件查询，在一对多中实现是更合理的
    @Test
    public void testR02() {
        Customer customer = new Customer();
        customer.setCustId(1L);
        customer.setCustName("xxx");

        List<Message> messages = repository.findByCustomer(customer);
        System.out.println(messages);
    }

    @Test
    public void testD01() {
        Customer customer = new Customer();
        customer.setCustId(1L);
        customer.setCustName("xxx");

        List<Message> messages = repository.findByCustomer(customer);

        repository.deleteAll(messages);
    }
}
