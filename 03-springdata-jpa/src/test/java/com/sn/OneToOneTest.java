package com.sn;

import com.sn.config.SpringDataJPAConfig;
import com.sn.pojo.Account;
import com.sn.pojo.Customer;
import com.sn.repositories.AccountRepository;
import com.sn.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OneToOneTest {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void testC01() {
        Account account = new Account();
        account.setUsername("wenwen");

        Customer customer = new Customer();
        customer.setCustName("文文");
        customer.setAccount(account);

        account.setCustomer(customer);
        customerRepository.save(customer);
    }

    /**
     * 为什么懒加载要配置事务 ：
     * 当通过repository调用完查询方法，session就会立即关闭， 一旦session你就不能查询，
     * 加了事务后， 就能让session直到事务方法执行完毕后才会关闭
     */
    @Test
    @Transactional(readOnly = true)
    public void testR02() {
        Optional<Customer> customer = customerRepository.findById(2L);   // 只查询出客户， session关闭
        System.out.println("=============");
        System.out.println(customer.get());  // toString
    }

    // 删除
    @Test
    public void testD03() {
        customerRepository.deleteById(1L);
    }

    //修改
    @Test
    public void testU03() {
        Customer customer = new Customer();
        customer.setCustId(3L);
        customer.setCustName("徐庶");
        // customer.setAccount(null);
        // customerRepository.save(customer);
        List<Account> accounts = accountRepository.findByCustomer(customer);
        System.out.println(accounts);
        accountRepository.deleteAll(accounts);

        /* Account account = byId.get();
        Customer customer = new Customer();
        customer.setCustId(10L);
        customer.setCustName("徐庶");
        customer.setAccount(account);
        customerRepository.save(customer);*/
    }
}
