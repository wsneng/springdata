package com.sn.springdata.service.impl;

import com.sn.springdata.pojo.AttrInfo;
import com.sn.springdata.pojo.BaseInfo;
import com.sn.springdata.pojo.FileInfo;
import com.sn.springdata.repositories.AttrInfoRepository;
import com.sn.springdata.repositories.BaseInfoRepository;
import com.sn.springdata.repositories.FileInfoRepository;
import com.sn.springdata.service.ScSYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScSYCServiceImpl implements ScSYCService {
    @Autowired
    private BaseInfoRepository baseInfoRepository;
    @Autowired
    private AttrInfoRepository attrInfoRepository;
    @Autowired
    private FileInfoRepository fileInfoRepository;
    @Override
    public BaseInfo saveBaseInfo(BaseInfo baseInfo) {
        return baseInfoRepository.save(baseInfo);
    }
    @Override
    public List<AttrInfo> saveAttrInfo(List list) {
        return attrInfoRepository.saveAll(list);
    }

    @Override
    public List<FileInfo> saveFileInfo(List list) {
        return fileInfoRepository.saveAll(list);
    }
}
