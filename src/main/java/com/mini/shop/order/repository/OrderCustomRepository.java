package com.mini.shop.order.repository;

import com.mini.shop.order.entity.Order;
import com.mini.shop.product.entity.Product;

import java.util.List;

public interface OrderCustomRepository {
    List<Order> getOrderList(String id);
    List<Product> getOrderDetail(String id, Long orderSeq);
}
