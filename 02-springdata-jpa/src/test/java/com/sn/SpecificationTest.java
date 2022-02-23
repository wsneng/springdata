package com.sn;

import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Customer;
import com.sn.repositories.CustomerSpecificationsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpecificationTest {
    @Autowired
    private CustomerSpecificationsRepository repository;

    @Test
    public void test1() {
        List<Customer> customerList = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // root from Customer,获取列
                // CriteriaBuilder  where 设置各种条件(> < in ..)
                // CriteriaQuery 组合(order by,where)
                return null;
            }
        });
        System.out.println(customerList);
    }

    /**
     * 查询客户范围 (in)
     * id  >大于
     * 地址  精确
     */
    @Test
    public void test2() {
        List<Customer> customerList = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // root from Customer,获取列
                // CriteriaBuilder  where 设置各种条件(> < in ..)
                // CriteriaQuery 组合(order by,where)

                Path<Long> custId = root.get("custId");
                Path<String> custName = root.get("custName");
                Path<String> custAddress = root.get("custAddress");

                Predicate PcustAddr = criteriaBuilder.equal(custAddress, "BEIJING");
                Predicate PcustId = criteriaBuilder.greaterThan(custId, 0L);
                CriteriaBuilder.In<String> in = criteriaBuilder.in(custName);
                in.value("wsn").value("asd");

                Predicate and = criteriaBuilder.and(PcustAddr, PcustId, in);
                return and;
            }
        });
        System.out.println(customerList);
    }

    /**
     * 动态
     */
    @Test
    public void test3() {
        Customer params = new Customer();
        params.setCustId(0L);
        params.setCustName("wsn");
        params.setCustAddress("zhuzhou");

        List<Customer> customerList = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // root from Customer,获取列
                // CriteriaBuilder  where 设置各种条件(> < in ..)
                // CriteriaQuery 组合(order by,where)

                // 1. 通过root拿到需要设置条件的字段
                Path<Long> custId = root.get("custId");
                Path<String> custName = root.get("custName");
                Path<String> custAddress = root.get("custAddress");

                // 2. 通过CriteriaBuilder设置不同类型条件
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(params.getCustAddress())) {
                    Predicate PcustAddr = criteriaBuilder.equal(custAddress, params.getCustAddress());
                    list.add(PcustAddr);
                }
                if (!StringUtils.isEmpty(params.getCustId())) {
                    Predicate PcustId = criteriaBuilder.greaterThan(custId, params.getCustId());
                    list.add(PcustId);
                }
                if (!StringUtils.isEmpty(params.getCustName())) {

                    CriteriaBuilder.In<String> in = criteriaBuilder.in(custName);
                    in.value(params.getCustName());
                    list.add(in);
                }


                Predicate and = criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
                return and;
            }
        });
        System.out.println(customerList);
    }

    /**
     * 排序
     */
    @Test
    public void test4() {
        Customer params = new Customer();
        params.setCustId(0L);
        params.setCustName("wsn");
        params.setCustAddress("zhuzhou");

        List<Customer> customerList = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // root from Customer,获取列
                // CriteriaBuilder  where 设置各种条件(> < in ..)
                // CriteriaQuery 组合(order by,where)

                // 1. 通过root拿到需要设置条件的字段
                Path<Long> custId = root.get("custId");
                Path<String> custName = root.get("custName");
                Path<String> custAddress = root.get("custAddress");

                // 2. 通过CriteriaBuilder设置不同类型条件
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(params.getCustAddress())) {
                    Predicate PcustAddr = criteriaBuilder.equal(custAddress, params.getCustAddress());
                    list.add(PcustAddr);
                }
                if (!StringUtils.isEmpty(params.getCustId())) {
                    Predicate PcustId = criteriaBuilder.greaterThan(custId, params.getCustId());
                    list.add(PcustId);
                }
                if (!StringUtils.isEmpty(params.getCustName())) {

                    CriteriaBuilder.In<String> in = criteriaBuilder.in(custName);
                    in.value(params.getCustName());
                    list.add(in);
                }


                Predicate and = criteriaBuilder.and(list.toArray(new Predicate[list.size()]));

                Order order = criteriaBuilder.asc(custId);

                return query.where(and).orderBy(order).getRestriction();
            }
        });
        System.out.println(customerList);
    }


}
