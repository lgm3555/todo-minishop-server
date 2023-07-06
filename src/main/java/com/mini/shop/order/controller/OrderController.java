package com.mini.shop.order.controller;

import com.mini.shop.error.exception.NotFoundUserException;
import com.mini.shop.order.dto.OrderDto;
import com.mini.shop.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getOrderList(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(orderService.getOrderList(request.getAttribute("id").toString()), HttpStatus.OK);
    }

    @GetMapping("/{orderSeq}")
    public ResponseEntity<?> getOrderDetail(HttpServletRequest request, @PathVariable Long orderSeq) {
        return new ResponseEntity<>(orderService.getOrderDetail(request.getAttribute("id").toString(), orderSeq), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertOrder(HttpServletRequest request, @Valid @RequestBody OrderDto orderDto) throws NotFoundUserException {
        return new ResponseEntity<>(orderService.insertOrder(orderDto, request.getAttribute("id").toString()), HttpStatus.OK);
    }
}