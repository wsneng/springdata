package com.sn.repositories;

import com.sn.pojo.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerMethodNameReposity extends PagingAndSortingRepository<Customer,Long> {

    List<Customer> findByCustName(String custName);

    Boolean existsByCustName(String custName);

    @Transactional
    @Modifying
    int deleteByCustId(Long id);
}
