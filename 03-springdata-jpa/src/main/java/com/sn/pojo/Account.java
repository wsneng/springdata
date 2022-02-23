package com.sn.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: wsn
 * @Date:2022/2/20 17:49
 * 一对一
 * 一个客户对一个账户
 */
@Entity
@Table(name = "tb_account")
@Data
/*@Getter         //  生成所有属性的get方法
@Setter        //  生成所有属性的set方法
@RequiredArgsConstructor  //  生成final属性的构造函数， 如果没有final就是无参构造函数
@EqualsAndHashCode*/
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", CustId=" + customer.getCustId() +
                ", CustName=" + customer.getCustName() +
                ", CustAddress=" + customer.getCustAddress() +
                '}';
    }
}
