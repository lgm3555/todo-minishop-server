package com.mini.shop.product.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tProduct")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCode;

    @Column(name = "image_url", length = 200)
    private String imageUrl;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "price", length = 50)
    private int price;

    @Column(name = "stock", length = 50)
    private int stock;

    @Column(name = "description", length = 150)
    private String description;

    @Column(name = "order_count", length = 50)
    private int orderCount;

    @Column(name = "register_date", length = 50)
    private String registerDate;

    @ManyToOne
    @JoinColumn(name = "category_code")
    private Category categoryCode;
}
