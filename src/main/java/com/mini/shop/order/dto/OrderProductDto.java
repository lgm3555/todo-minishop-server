package com.mini.shop.order.dto;

import com.mini.shop.order.entity.OrderProduct;
import com.mini.shop.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto {

    private Product product;

    private int count;

    private int amount;

    public static OrderProductDto convertToDto(OrderProduct orderProduct) {
        OrderProductDto orderProductDto = new OrderProductDto();
        orderProductDto.setProduct(orderProduct.getProduct());
        orderProductDto.setCount(orderProduct.getCount());
        orderProductDto.setAmount(orderProduct.getAmount());
        return orderProductDto;
    }
}
