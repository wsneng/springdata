package com.sn.springdata.service;

import com.sn.springdata.pojo.AttrInfo;
import com.sn.springdata.pojo.BaseInfo;
import com.sn.springdata.pojo.Customer;
import com.sn.springdata.pojo.FileInfo;

import java.util.List;

public interface ScSYCService {
    BaseInfo saveBaseInfo(BaseInfo baseInfo);

    List<AttrInfo> saveAttrInfo(List list);

    List<FileInfo> saveFileInfo(List list);


}
