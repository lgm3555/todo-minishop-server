package com.mini.shop.order.dto;

import com.mini.shop.order.entity.Order;
import com.mini.shop.order.entity.OrderStatement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private long orderSeq;

    private String id;

    private String reciver;

    private String phone;

    private String email;

    private String address;

    private String orderDate;

    private String paymentAmount;

    private OrderStatement statement;

    private int orderProductCount;

    private Set<OrderProductDto> orderProductDto;

    public static OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderSeq(order.getOrderSeq());
        orderDto.setId(order.getMember().getId());
        orderDto.setReciver(order.getReciver());
        orderDto.setPhone(order.getPhone());
        orderDto.setEmail(order.getEmail());
        orderDto.setAddress(order.getAddress());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setPaymentAmount(order.getPaymentAmount());
        orderDto.setStatement(order.getStatement());

        return orderDto;
    }
}
