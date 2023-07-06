package com.mini.shop.order.dto;

import com.mini.shop.order.entity.Order;
import com.mini.shop.order.entity.OrderProduct;
import com.mini.shop.product.entity.Product;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto {

    private Long orderSeq;

    private Long productCode;

    private int count;

    private int amount;

    public static OrderProductDto convertToDto(OrderProduct orderProduct) {
        OrderProductDto orderProductDto = new OrderProductDto();
        orderProductDto.setOrderSeq(orderProduct.getOrder().getOrderSeq());
        orderProductDto.setProductCode(orderProduct.getProduct().getProductCode());
        orderProductDto.setCount(orderProduct.getCount());
        orderProductDto.setAmount(orderProduct.getAmount());
        return orderProductDto;
    }
}
