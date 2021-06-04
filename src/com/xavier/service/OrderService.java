package com.xavier.service;

import com.xavier.domain.Item;
import com.xavier.domain.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface OrderService {
    Order makeOrder(int user_id, Map<Item,Integer> cart) throws SQLException;
    List<Order> getOrders(int user_id) throws SQLException;
    Order getOrder(int order_id) throws SQLException;
    List<Order> getAllOrders() throws SQLException;
    Map<Item,Integer> getOrderDetail(int order_id) throws SQLException;
}
