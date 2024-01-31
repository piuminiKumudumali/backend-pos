package com.ijse.ijse103databasepos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.ijse103databasepos.dto.OrderDto;
import com.ijse.ijse103databasepos.entity.Order;

@Service
public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(OrderDto orderDto);
}
