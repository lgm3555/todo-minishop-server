package com.mini.shop.product.dto;

import com.mini.shop.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long productCode;

    private String imageUrl;

    private String productName;

    private int price;

    private int stock;

    private String description;

    private int orderCount;

    private String registerDate;

    public static ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductCode(product.getProductCode());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setDescription(product.getDescription());
        productDto.setOrderCount(product.getOrderCount());
        productDto.setRegisterDate(product.getRegisterDate());
        return productDto;
    }
}
