package com.sn.repositories;

import com.sn.pojo.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerQBEReposity extends PagingAndSortingRepository<Customer, Long>,
        QueryByExampleExecutor<Customer> {

}
