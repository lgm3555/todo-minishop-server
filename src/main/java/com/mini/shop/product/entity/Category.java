package com.mini.shop.product.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tCategory")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @Column(name = "category_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryCode;

    @Column(name = "category_name", length = 50)
    private String categoryName;
}
