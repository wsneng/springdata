package com.sn;

import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Customer;
import com.sn.pojo.Message;
import com.sn.pojo.Role;
import com.sn.repositories.CustomerRepository;
import com.sn.repositories.MessageRepository;
import com.sn.repositories.RoleRepository;
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
public class ManyToManyTest {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 如果保存的关联数据，使用已有的，需要从数据库查询出来（持久状态）  否则提示游离状态不能持久化
     * 如果一个业务方法有多个持久化操作，记得加上@Transactional，否则不能共用一个session
     * 在单元测试中用到了@Transactional,如果有增删改的操作一定要加 @Commit  针对单元测试
     * 单元测试认为事务方法@Transactional,只是测试，不会为你提交事务，需要单独加@Commit
     */
    @Test
    @Transactional   // 涉及到查询和保存，查询关掉session变成游离状态了
    @Commit
    public void testC02() {
        List<Role> roles = new ArrayList<>();
        // roles.add(new Role("超级管理员"));
        // roles.add(new Role("商品管理员"));
        roles.add(roleRepository.findById(5L).get());
        roles.add(roleRepository.findById(6L).get());

        Customer customer = new Customer();
        customer.setCustName("周瑜3");
        customer.setRoles(roles);

        repository.save(customer);
    }

    @Test
    @Transactional(readOnly = true)
    public void testR01() {
        Optional<Customer> byId = repository.findById(1L);
        System.out.println(byId.get());
    }

    /**
     * @Commit 单元测试要加否则回滚数据
     * 多对多其实不适合删除，因为经常出现数据可能除了和当前这段关联还回关联另一端，
     * 此时删除就会：ConstraintViolationException
     * 要删除要保证没有其余额外其他另一端关联
     */
    @Test
    @Transactional
    @Commit
    public void testD01() {
        Optional<Customer> byId = repository.findById(1L);
        repository.delete(byId.get());
    }


}
