package com.sn;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Customer;
import com.sn.pojo.QCustomer;
import com.sn.repositories.CustomerMethodNameReposity;
import com.sn.repositories.CustomerQueryDSLRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class QueryDSLTest {
    @Autowired
    private CustomerQueryDSLRepository repository;

    @Test
    public void test1(){
        QCustomer customer = QCustomer.customer;
        BooleanExpression eq = customer.custId.eq(1L);
        System.out.println(repository.findOne(eq));

    }

    /**
     * 查询客户名称范围(in)
     * id > 大于
     * 地址 精确
     *  等于 EQ : equal .eq
     *  不等于 NE : not equal .ne
     *  小于 LT : less than .lt
     *  大于 GT : greater than .gt
     *  小于等于 LE : less than or equal .loe
     *  大于等于 GE : greater than or equal .goe
     */
    @Test
    public void test2(){
        QCustomer customer = QCustomer.customer;
        // 通过id查询
        BooleanExpression and = customer.custName.in("wsn", "wfj")
                .and(customer.custId.gt(0L))
                .and(customer.custAddress.eq("zhuzhou"));
        System.out.println(repository.findAll(and));

    }
}
