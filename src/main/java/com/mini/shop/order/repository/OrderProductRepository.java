package com.mini.shop.order.repository;

import com.mini.shop.order.entity.OrderProduct;
import com.mini.shop.order.entity.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
