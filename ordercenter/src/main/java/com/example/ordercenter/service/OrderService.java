package com.example.ordercenter.service;

import com.example.ordercenter.entity.Order;

public interface OrderService {
    int addOrder(Order order);

    Order getOrderById(String orderId);
}
