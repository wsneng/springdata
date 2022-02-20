package com.sn;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Customer;
import com.sn.pojo.QCustomer;
import com.sn.repositories.CustomerQueryDSLRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    /**
     * 动态
     */
    @Test
    public void test3(){

        Customer params = new Customer();
        params.setCustId(0L);
        params.setCustName("wsn,wc");
        params.setCustAddress("zhuzhou");
        QCustomer customer = QCustomer.customer;
        // 初始条件 类似于1=1 永远都成立
        BooleanExpression expression = customer.isNotNull().or(customer.isNull());

        expression = params.getCustId()>-1 ? expression.and(customer.custId.gt(params.getCustId())) : expression;

        expression = !StringUtils.isEmpty(params.getCustName()) ? expression.and(customer.custName.in(params.getCustName().split(","))) : expression;
        expression = !StringUtils.isEmpty(params.getCustAddress()) ? expression.and(customer.custAddress.eq(params.getCustAddress())) : expression;
        System.out.println(repository.findAll(expression));

    }

    /**
     * @Autowired 存在线程安全问题
     * 一个Bean默认单例
     *
     *
     */
    @PersistenceContext
    EntityManager em;
    /**
     * 自定义列查询  分组
     * 需要使用原生态方式(Specification)
     * 通过repository进行查询 表和列都是固定的
     */
    @Test
    public void test4(){
        Customer params = new Customer();
        params.setCustId(0L);
        params.setCustName("wsn,wc");
        params.setCustAddress("zhuzhou");
        QCustomer customer = QCustomer.customer;

        JPAQueryFactory factory = new JPAQueryFactory(em);

        // 构建基于QueryDSL的查询
        JPAQuery<Tuple> tupleJPAQuery = factory.select(customer.custId, customer.custName)
                .from(customer)
                .orderBy(customer.custId.desc());
        // 执行查询
        List<Tuple> fetch = tupleJPAQuery.fetch();
        //处理返回数据
        for (Tuple tuple : fetch) {
            System.out.println(tuple.get(customer.custId));
            System.out.println(tuple.get(customer.custName));
        }
    }
    /**
     * 聚合
     */
    @Test
    public void test5(){
        Customer params = new Customer();
        params.setCustId(0L);
        params.setCustName("wsn,wc");
        params.setCustAddress("zhuzhou");
        QCustomer customer = QCustomer.customer;

        JPAQueryFactory factory = new JPAQueryFactory(em);

        // 构建基于QueryDSL的查询
        JPAQuery<Long> longJPAQuery = factory.select(customer.custId.sum())
                .from(customer)
                .where(customer.custId.lt(4L))
                .orderBy(customer.custId.desc());
        // 执行查询
        List<Long> fetch = longJPAQuery.fetch();
        //处理返回数据
        for (Long tuple : fetch) {
            System.out.println(tuple);
        }
    }
}
