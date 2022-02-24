package com.sn.springdata.repositories;

import com.sn.springdata.pojo.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, String> {

}
