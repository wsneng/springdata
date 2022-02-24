package com.sn.springdata.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity    // 作为hibernate的实体类
@Table(name = "FILEINFO")      //映射的表名
public class FileInfo {
    @Id
    private String	FILEUNID;
    private String	FILENAME;
    private String	FILEURL;
    private String	FILEPWD;
    private String	UNID;
}
