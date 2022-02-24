package com.sn.springdata.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity    // 作为hibernate的实体类
@Table(name = "BASEINFO")      //映射的表名
public class BaseInfo {

    private String	CALLER;
    private String	CALLTIME;
    private String	CALLBACK_URL;;
    private String	ISSUE;
    @Id
    private String	PROJID;
    private String	PROJPWD;
    private String	IS_MANUBRIUM;
    private String	BASE_CODE;
    private String	IMPLEMENT_CODE;
    private String	TASKHANDLEITEM;
    private String	SERVICECODE;
    private String	SERVICE_DEPTID;
    private String	BUS_MODE;
    private String	BUS_MODE_DESC;
    private String	SERVICEVERSION;
    private String	SERVICENAME;
    private String	PROJECTNAME;
    private String	INFOTYPE;
    private String	APPLY_TYPE;
    private String	BUS_TYPE;
    private String	REL_BUS_ID;
    private String	APPLYNAME;
    private String	APPLY_CARDTYPE;
    private String	APPLY_CARDNUMBER;
    private String	CONTACTMAN;
    private String	CONTACTMAN_CARDTYPE;
    private String	CONTACTMAN_CARDNUMBER;
    private String	TELPHONE;
    private String	MOBILE;
    private String	POSTCODE;
    private String	ADDRESS;
    private String	LEGALMAN;
    private String	DEPTID;
    private String	DEPTNAME;
    private String	APPLYFROM;
    private String	APPROVE_TYPE;
    private String	APPLY_PROPERTIY;
    private String	RECEIVETIME;
    private String	BELONGTO;
    private String	AREACODE;
    private String	DATASTATE;
    private String	BELONGSYSTEM;
    private String	EXTEND;
    private String	DATAVERSION;
    private String	SYNC_STATUS;
    private String	RECEIVE_USEID;
    private String	RECEIVE_NAME;
    private String	CREATE_TIME;
    private String	SS_ORGCODE;
    private String	MEMO;



}
