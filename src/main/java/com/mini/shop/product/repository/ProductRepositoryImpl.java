package com.mini.shop.product.repository;

import com.mini.shop.product.entity.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mini.shop.product.entity.QProduct.product;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> getPopularProductList() {

        List<Product> result = jpaQueryFactory
                .selectFrom(product)
                .orderBy(product.orderCount.desc())
                .limit(10)
                .fetch();

        return result;
    }
}
