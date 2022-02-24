package com.sn.springdata.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity    // 作为hibernate的实体类
@Table(name = "ATTRINFO")      //映射的表名
public class AttrInfo {
    @Id
    private String	UNID;
    private String	PROJID;
    private String	ATTRNAME;
    private String	SORTID;
    private String	TAKETYPE;
    private String	ISTAKE;
    private String	AMOUNT;
    private String	TAKETIME;
    private String	MEMO;
    private String	BELONGSYSTEM;
    private String	AREACODE;
    private String	EXTEND;
    private String	DATAVERSION;
    private String	SYNC_STATUS;
    private String	CREATE_TIME;
    private String	ATTRID;
    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "UNID")
    // private List<FileInfo> FILEINFO;
}
