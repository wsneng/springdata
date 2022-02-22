package com.sn.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_message")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String info;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // 一定要有
    public Message() {
    }

    public Message(String info) {
        this.info = info;
    }

    public Message(String info, Customer customer) {
        this.info = info;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", custId=" + customer.getCustId() +
                ", custName=" + customer.getCustName() +
                ", custAddress=" + customer.getCustAddress() +
                '}';
    }
}
