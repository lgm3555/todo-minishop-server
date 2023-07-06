package com.mini.shop.order.dto;

import com.mini.shop.auth.entity.Member;
import com.mini.shop.order.entity.Order;
import com.mini.shop.order.entity.OrderStatement;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long orderSeq;

    private Member member;

    private String reciver;

    private String phone;

    private String email;

    private String address;

    private String orderDate;

    private int paymentAmount;

    private OrderStatement statement;

    private List<OrderProductDto> orderProduct;

    public static OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderSeq(order.getOrderSeq());
        orderDto.setMember(order.getMember());
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
