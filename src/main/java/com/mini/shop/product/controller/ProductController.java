package com.mini.shop.product.controller;

import com.mini.shop.product.dto.ProductDto;
import com.mini.shop.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/category/{categoryCode}")
    public ResponseEntity<?> getCategoryProduct(@PathVariable Long categoryCode) {
        List<ProductDto> productDtoList = productService.getCategoryProduct(categoryCode);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<?> getProduct(@PathVariable Long productCode) {
        ProductDto productDto = productService.getProduct(productCode);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
