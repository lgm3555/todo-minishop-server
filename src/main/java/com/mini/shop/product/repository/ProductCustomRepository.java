package com.mini.shop.product.repository;

import com.mini.shop.product.entity.Product;

import java.util.List;

public interface ProductCustomRepository {
    List<Product> getPopularProductList();
}
