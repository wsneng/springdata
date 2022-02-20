package com.sn.repositories;

import com.sn.pojo.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface CustomerSpecificationsRepository extends PagingAndSortingRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

}
