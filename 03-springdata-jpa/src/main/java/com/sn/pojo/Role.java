package com.sn.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity    // 作为hibernate的实体类
@Table(name = "tb_roles")      //映射的表名
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String rName;

    public Role() {
    }

    public Role(String rName) {
        this.rName = rName;
    }

    public Role(Long id, String rName) {
        this.id = id;
        this.rName = rName;
    }
}
