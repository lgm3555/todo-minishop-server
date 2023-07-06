package com.mini.shop.order.entity;

import com.mini.shop.auth.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tOrder")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "oreder_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderSeq;

    @Column(name = "reciver")
    private String reciver;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "payment_amount")
    private int paymentAmount;

    @Column(name = "statement")
    @Enumerated(EnumType.STRING)
    private OrderStatement statement;

    @ManyToOne
    @JoinColumn(name = "id")
    private Member member;
}
