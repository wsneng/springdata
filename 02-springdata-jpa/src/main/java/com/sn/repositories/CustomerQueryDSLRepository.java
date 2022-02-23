package com.sn.repositories;

import com.sn.pojo.Customer;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CustomerQueryDSLRepository extends PagingAndSortingRepository<Customer, Long>, QuerydslPredicateExecutor<Customer> {

}
