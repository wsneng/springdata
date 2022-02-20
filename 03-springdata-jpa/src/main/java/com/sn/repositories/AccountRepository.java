package com.sn.repositories;

import com.sn.pojo.Account;
import com.sn.pojo.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account,Long> {

}
