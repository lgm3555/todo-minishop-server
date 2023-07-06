package com.mini.shop.order.service;

import com.mini.shop.auth.entity.Member;
import com.mini.shop.auth.repository.UserRepository;
import com.mini.shop.error.exception.NotFoundUserException;
import com.mini.shop.order.dto.OrderDto;
import com.mini.shop.order.dto.OrderProductDto;
import com.mini.shop.order.entity.Order;
import com.mini.shop.order.entity.OrderProduct;
import com.mini.shop.order.repository.OrderProductRepository;
import com.mini.shop.order.repository.OrderRepository;
import com.mini.shop.product.dto.ProductDto;
import com.mini.shop.product.entity.Product;
import com.mini.shop.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
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

    @Transactional
    public OrderDto insertOrder(OrderDto orderDto, String id) throws NotFoundUserException {
        Member member = userRepository.findById(id).orElseThrow(() -> new NotFoundUserException("사용자를 찾을 수 없습니다."));
        orderDto.setMember(member);

        Order order = convertToEntity(orderDto);
        Order orderResult = orderRepository.save(order);

        List<OrderProductDto> orderProductDtoList = insertOrderProduct(orderDto.getOrderProduct(), order);

        OrderDto result = OrderDto.convertToDto(orderResult);
        result.setOrderProduct(orderProductDtoList);

        return result;
    }
    
    private List<OrderProductDto> insertOrderProduct(List<OrderProductDto> orderProductDtoList, Order order) {
        List<OrderProductDto> result = new ArrayList<>();

        if (orderProductDtoList != null) {
            for (OrderProductDto orderProductDto : orderProductDtoList) {
                // 상품 체크
                Product product = productRepository.findById(orderProductDto.getProductCode()).get();

                if (product != null) {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setOrder(order);
                    orderProduct.setProduct(product);
                    orderProduct.setAmount(orderProductDto.getAmount());
                    orderProduct.setCount(orderProductDto.getCount());

                    result.add(OrderProductDto.convertToDto(orderProductRepository.save(orderProduct)));
                }
            }
        }

        return result;
    }

    private Order convertToEntity(OrderDto orderDto) {
        Order order = new Order();
        if (!String.valueOf(orderDto.getOrderSeq()).equals("")) order.setOrderSeq(orderDto.getOrderSeq());
        if (orderDto.getMember() != null) order.setMember(orderDto.getMember());
        if (!orderDto.getReciver().equals("")) order.setReciver(orderDto.getReciver());
        if (!orderDto.getPhone().equals("")) order.setPhone(orderDto.getPhone());
        if (!orderDto.getEmail().equals("")) order.setEmail(orderDto.getEmail());
        if (!orderDto.getAddress().equals("")) order.setAddress(orderDto.getAddress());
        if (!orderDto.getOrderDate().equals("")) order.setOrderDate(orderDto.getOrderDate());
        if (!String.valueOf(orderDto.getPaymentAmount()).equals("")) order.setPaymentAmount(orderDto.getPaymentAmount());
        if (!orderDto.getStatement().equals("")) order.setStatement(orderDto.getStatement());
        return order;
    }
}
