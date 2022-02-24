package com.sn.springdata.pojo;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity    // 作为hibernate的实体类
@Table(name = "t_customer")      //映射的表名
@Data
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;   // 客户的主键

    @Column(name = "cust_name")
    private String custName;    // 客户名称

    @Column(name = "cust_address")
    private String custAddress; //客户地址

}
