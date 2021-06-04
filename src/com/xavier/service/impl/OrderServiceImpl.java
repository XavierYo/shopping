package com.xavier.service.impl;

import com.xavier.dao.OrderDao;
import com.xavier.domain.Item;
import com.xavier.domain.Order;
import com.xavier.service.OrderService;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order makeOrder(int user_id, Map<Item,Integer> cart) throws SQLException {
        OrderDao orderDao = new OrderDao();
        Order order = new Order();
        order.setUser_id(user_id);
        double total_amount = 0;
        Set<Map.Entry<Item,Integer>> entries = cart.entrySet();
        for (Map.Entry<Item,Integer> entry : entries){
            total_amount+=entry.getKey().getPrice()*entry.getValue();
        }
        order.setTotal_amount(total_amount);
        int order_id = orderDao.addOrder(order,cart);
        return orderDao.getOrder(order_id);
    }

    @Override
    public List<Order> getOrders(int user_id) throws SQLException{
        OrderDao orderDao = new OrderDao();
        return orderDao.getOrders(user_id);
    }

    @Override
    public Order getOrder(int order_id) throws SQLException {
        OrderDao orderDao = new OrderDao();
        return orderDao.getOrder(order_id);
    }

    @Override
    public List<Order> getAllOrders() throws SQLException{
        OrderDao orderDao = new OrderDao();
        return orderDao.getAllOrders();
    }

    @Override
    public Map<Item,Integer> getOrderDetail(int order_id) throws SQLException{
        OrderDao orderDao = new OrderDao();
        return orderDao.getOrderDetail(order_id);
    }
}
