package com.mini.shop.order.service;

import com.mini.shop.auth.entity.Member;
import com.mini.shop.auth.repository.UserRepository;
import com.mini.shop.error.exception.NotFoundUserException;
import com.mini.shop.order.dto.OrderDto;
import com.mini.shop.order.dto.OrderProductDto;
import com.mini.shop.order.entity.Order;
import com.mini.shop.order.entity.OrderProduct;
import com.mini.shop.order.repository.OrderRepository;
import com.mini.shop.product.dto.ProductDto;
import com.mini.shop.product.entity.Product;
import com.mini.shop.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<OrderDto> getOrderList(String id) {
        List<Order> orderList = orderRepository.getOrderList(id);
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order order : orderList) {
            OrderDto orderDto = OrderDto.convertToDto(order);
            orderDtoList.add(orderDto);
        }

        return orderDtoList;
    }

    public List<ProductDto> getOrderDetail(String id, Long orderSeq) {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = orderRepository.getOrderDetail(id, orderSeq);

        for (Product product : productList) {
            ProductDto productDto = ProductDto.convertToDto(product);
            productDtoList.add(productDto);
        }

        return productDtoList;
    }

    //TODO 주문 입력
    public OrderDto insertOrder(OrderDto orderDto) throws NotFoundUserException {
        int totalPrice = 0;
        Member member = userRepository.findById(orderDto.getId()).orElseThrow(() -> new NotFoundUserException("사용자를 찾을 수 없습니다."));
        List<OrderProduct> orderProductList = new ArrayList<>();

        Set<OrderProductDto> orderProductDtoList = orderDto.getOrderProductDto();
        if (orderProductList != null) {
            for (OrderProductDto orderProductDto : orderProductDtoList) {
                Product product = productRepository.findById(orderProductDto.getProduct().getProductCode()).get();
                totalPrice += product.getPrice() * orderProductDto.getCount();

                OrderProduct orderProduct = convertToEntity(orderProductDto);
                orderProductList.add(orderProduct);
            }
        } else {

        }

        Order order = convertToEntity(orderDto);
        return orderDto.convertToDto(orderRepository.save(order));
    }

    private Order convertToEntity(OrderDto orderDto) {
        Order order = new Order();
        if (!String.valueOf(orderDto.getOrderSeq()).equals("")) order.setOrderSeq(orderDto.getOrderSeq());
        if (!orderDto.getReciver().equals("")) order.setReciver(orderDto.getReciver());
        if (!orderDto.getPhone().equals("")) order.setPhone(orderDto.getPhone());
        if (!orderDto.getEmail().equals("")) order.setEmail(orderDto.getEmail());
        if (!orderDto.getAddress().equals("")) order.setAddress(orderDto.getAddress());
        if (!orderDto.getOrderDate().equals("")) order.setOrderDate(orderDto.getOrderDate());
        if (!orderDto.getPaymentAmount().equals("")) order.setPaymentAmount(orderDto.getPaymentAmount());
        if (!orderDto.getStatement().equals("")) order.setStatement(orderDto.getStatement());
        return order;
    }

    private OrderProduct convertToEntity(OrderProductDto orderProductDto) {
        OrderProduct orderProduct = new OrderProduct();
        if (orderProductDto.getProduct() != null) orderProduct.setProduct(orderProductDto.getProduct());
        if (!String.valueOf(orderProductDto.getCount()).equals("")) orderProduct.setCount(orderProductDto.getCount());
        if (!String.valueOf(orderProductDto.getAmount()).equals("")) orderProduct.setAmount(orderProductDto.getAmount());
        return orderProduct;
    }
}
