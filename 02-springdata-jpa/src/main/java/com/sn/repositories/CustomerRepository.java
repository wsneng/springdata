package com.sn.repositories;

import com.sn.pojo.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    // // 自定义根据custName查询  索引
    // @Query("FROM Customer WHERE custName = ?1")
    // List<Customer> findCustomerByCustName(String custName);
    // 自定义根据custName查询  具名
    @Query("FROM Customer WHERE custName=:custName")
    List<Customer> findCustomerByCustName(@Param("custName") String custName);

    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.custName=:custName WHERE c.custId=:id")
    int updateCustomer(@Param("custName") String custName, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("delete from Customer c WHERE c.custName=:custName")
    int deleteCustomer(@Param("custName") String custName);

    //新增 JPQL
    @Transactional
    @Modifying       // 通过springdatajpa 是增删改操作
    @Query("INSERT INTO Customer(custName) SELECT c.custName from Customer c where c.custId=:id")
    int insertCustomer(@Param("id") Long id);

    // 原生SQL
    @Query(value = "SELECT * FROM cst_customer where cust_name=:custName", nativeQuery = true)
    List<Customer> findBySQL(@Param("custName") String custName);
}
