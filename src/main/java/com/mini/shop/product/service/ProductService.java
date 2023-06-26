package com.mini.shop.product.service;

import com.mini.shop.product.dto.ProductDto;
import com.mini.shop.product.entity.Category;
import com.mini.shop.product.entity.Product;
import com.mini.shop.product.repository.CategoryRepository;
import com.mini.shop.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDto> getCategoryProduct(Long categoryCode) {
        List<Product> productList = new ArrayList<>();
        List<ProductDto> productDtoList = new ArrayList<>();

        if (!getCategoryCheck(categoryCode)) {
            logger.info("인기상품");
            productList = productRepository.getPopularProductList();
        } else {
            Category category = new Category(categoryCode);
            productList = productRepository.findByCategoryCode(category);
        }

        for (Product product : productList) {
            ProductDto productDto = ProductDto.convertToDto(product);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    public Boolean getCategoryCheck(Long id) {
        return categoryRepository.existsById(id);
    }

    public ProductDto getProduct(Long productCode) {
        Product result = productRepository.getById(productCode);
        return ProductDto.convertToDto(result);
    }

    private Product convertToEntity(ProductDto productDto) {
        Product product = new Product();
        if (!productDto.getProductCode().equals("")) product.setProductCode(productDto.getProductCode());
        if (!productDto.getImageUrl().equals("")) product.setImageUrl(productDto.getImageUrl());
        if (!productDto.getProductName().equals("")) product.setProductName(productDto.getProductName());
        if (!String.valueOf(productDto.getPrice()).equals("")) product.setPrice(productDto.getPrice());
        if (!String.valueOf(productDto.getStock()).equals("")) product.setStock(product.getStock());
        if (!productDto.getDescription().equals("")) product.setDescription(productDto.getDescription());
        if (!String.valueOf(productDto.getOrderCount()).equals("")) product.setOrderCount(product.getOrderCount());
        if (!productDto.getRegisterDate().equals("")) product.setRegisterDate(productDto.getRegisterDate());
        return product;
    }
}
