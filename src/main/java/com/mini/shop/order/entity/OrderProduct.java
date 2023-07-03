package com.mini.shop.order.entity;

import com.mini.shop.product.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tOrderProduct")
@Getter
@Setter
@NoArgsConstructor
public class OrderProduct implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_seq")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_code")
    private Product product;

    @Column(name = "count")
    private int count;

    @Column(name = "amount")
    private int amount;
}
