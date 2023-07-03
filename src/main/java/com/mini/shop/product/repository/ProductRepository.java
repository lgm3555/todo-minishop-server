package com.mini.shop.product.repository;

import com.mini.shop.product.entity.Category;
import com.mini.shop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCustomRepository {
    List<Product> findByCategory(Category category);
}
