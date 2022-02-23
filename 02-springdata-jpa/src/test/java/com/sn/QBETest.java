package com.sn;

import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Customer;
import com.sn.repositories.CustomerMethodNameReposity;
import com.sn.repositories.CustomerQBEReposity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class QBETest {
    @Autowired
    CustomerQBEReposity customerQBEReposity;

    /**
     * 客户地址动态查询
     */
    @Test
    public void test1() {
        // 查询条件
        Customer customer = new Customer();
        customer.setCustName("S");
        customer.setCustAddress("zhuzhou");
        // 通过匹配器，对条件行为进行设置
        ExampleMatcher matcher = ExampleMatcher.matching()
                // .withIgnorePaths("custAddress")   // 设置忽略的属性
                // .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)    // 模糊查询包含
                // 针对单个条件进行模糊查询,它会使withIgnoreCase失效，需要单独设置
                .withMatcher("custName", m -> m.contains().ignoreCase())
                .withIgnoreCase();      // 忽略大小写

        // 通过Example构建查询条件
        Example<Customer> example = Example.of(customer, matcher);
        List<Customer> all = (List<Customer>) customerQBEReposity.findAll(example);
        System.out.println(all);
    }

}
