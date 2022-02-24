package com.sn.springdata.repositories;

import com.sn.springdata.pojo.BaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseInfoRepository extends JpaRepository<BaseInfo, String> {

}