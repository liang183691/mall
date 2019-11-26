package com.example.ordercenter.web;

import com.example.common.ResponseResult;
import com.example.ordercenter.entity.Order;
import com.example.ordercenter.service.OrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseResult getOrderById(@RequestParam("orderId") String orderId){
        Order order = orderService.getOrderById(orderId);
        return ResponseResult.success(order);
    }

    @PostMapping
    public ResponseResult addOrder(@RequestBody Order order){
        orderService.addOrder(order);
        return ResponseResult.success(order);
    }

    @PostMapping("/addOrder1")
    public ResponseResult addOrder1(@RequestBody Order order){
        orderService.addOrder1(order);
        return ResponseResult.success(order);
    }

}
