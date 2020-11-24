package com.xavier.service;

import com.xavier.domain.Cart;
import com.xavier.domain.Order;

import java.util.List;

public interface OrderService {
    Order makeOrder(List<Cart> carts);
    List<Order> getOrders(int user_id);
    Order getOrder(int order_id);
    List<Order> getAllOrders();
}
