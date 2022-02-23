package com.sn;

import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Customer;
import com.sn.repositories.CustomerMethodNameReposity;
import com.sn.repositories.CustomerQueryDSLRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class NameTest {
    @Autowired
    private CustomerMethodNameReposity customerMethodNameReposity;

    @Test
    public void test1() {
        List<Customer> customers = customerMethodNameReposity.findByCustName("wsn");
        System.out.println(customers);
        Boolean exists = customerMethodNameReposity.existsByCustName("wsn");
        System.out.println(exists);
        int deleteByCustId = customerMethodNameReposity.deleteByCustId(8L);
        System.out.println(deleteByCustId);
    }

}
