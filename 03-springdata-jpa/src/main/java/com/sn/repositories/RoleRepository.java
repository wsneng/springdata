package com.sn.repositories;

import com.sn.pojo.Customer;
import com.sn.pojo.Message;
import com.sn.pojo.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

}
