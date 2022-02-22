package com.sn.repositories;

import com.sn.pojo.Account;
import com.sn.pojo.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AccountRepository extends PagingAndSortingRepository<Account,Long> {

    List<Account> findByCustomer(Customer customer);
}
