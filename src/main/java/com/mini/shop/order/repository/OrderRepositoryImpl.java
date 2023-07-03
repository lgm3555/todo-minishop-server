package com.mini.shop.order.repository;

import com.mini.shop.order.entity.Order;
import com.mini.shop.product.entity.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mini.shop.order.entity.QOrder.order;
import static com.mini.shop.order.entity.QOrderProduct.orderProduct;
import static com.mini.shop.product.entity.QProduct.product;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Order> getOrderList(String id) {
        List<Order> result = jpaQueryFactory
                .selectFrom(order)
                .where(order.member.id.eq(id))
                .limit(10)
                .fetch();

        return result;
    }

    @Override
    public List<Product> getOrderDetail(String id, Long orderSeq) {
        List<Product> result = jpaQueryFactory
                .select(product)
                .from(orderProduct)
                .join(orderProduct.product, product)
                .where(orderProduct.order.orderSeq.eq(orderSeq))
                .fetch();

        return result;
    }
}
