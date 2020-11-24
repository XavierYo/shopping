package com.xavier.service.impl;

import com.xavier.domain.Cart;
import com.xavier.domain.Item;
import com.xavier.domain.Order;
import com.xavier.service.ItemService;
import com.xavier.service.OrderService;
import com.xavier.utils.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order makeOrder(List<Cart> carts) {
        //购物车空，直接返回
        if(carts==null) return null;

        double total_amount=0;
        for(Cart cart:carts){
            total_amount+=cart.getItem().getPrice()*cart.getNumber();
        }

        Order order=new Order();
        order.setTotal_amount(total_amount);
        order.setUser_id(carts.get(0).getUser_id());
        try{

            DataBaseConnection dbc =new DataBaseConnection();
            Connection conn=dbc.getConn();
            String sql ="INSERT INTO order_info (total_amount, user_id) VALUES (?,?)";
            PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1,total_amount);
            ps.setInt(2,carts.get(0).getUser_id());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();//用于获取自动生成的订单id
            int order_id=-1;

            if(rs.next()){
                order_id=rs.getInt(1);
            }
            order.setOrder_id(order_id);
            ItemService itemService=new ItemServiceImpl();

            for(Cart cart:carts){
                Item item=cart.getItem();
                int item_id=item.getItemID();
                int item_num=item.getItemNumber();
                int cart_num=cart.getNumber();
                item.setItemNumber(item_num-cart_num);
                itemService.modItem(item_id,item);
                String sql_detail="INSERT INTO order_detail (order_id,item_id,num) VALUES(?,?,?)";
                ps=conn.prepareStatement(sql_detail);
                ps.setInt(1,order_id);
                ps.setInt(2,cart.getItem().getItemID());
                ps.setInt(3,cart.getNumber());
                ps.executeUpdate();
            }
            OrderService orderService=new OrderServiceImpl();
            order=orderService.getOrder(order_id);
            dbc.close();
            return order;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getOrders(int user_id) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql= "SELECT * FROM order_info WHERE user_id="+user_id;
        ResultSet rs=dbc.query(sql);
        List<Order> orders=new ArrayList<>();
        try{
            while(rs.next()){
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setTotal_amount(rs.getDouble("total_amount"));
                order.setCreate_time(rs.getTimestamp("create_time"));
                orders.add(order);
            }
            dbc.close();
            return orders;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order getOrder(int order_id) {
        Order order=new Order();
        order.setOrder_id(order_id);
        DataBaseConnection dbc=new DataBaseConnection();
        String sql1="SELECT * FROM order_info WHERE order_id="+order_id;
        ResultSet rs1=dbc.query(sql1);
        try{
            if(rs1.next()){
                order.setTotal_amount(rs1.getDouble("total_amount"));
                order.setCreate_time(rs1.getTimestamp("create_time"));
                order.setUser_id(rs1.getInt("user_id"));
            }else return null;
        }catch (SQLException e){
            e.printStackTrace();
        }
        //订单详情
        List<Item> items=new ArrayList<>();
        String sql2="SELECT * FROM order_detail WHERE order_id="+order_id;
        ResultSet rs2=dbc.query(sql2);
        ItemService itemService=new ItemServiceImpl();
        try{
            while(rs2.next()){
                Item item=new Item();
                item =itemService.getItemByID(rs2.getInt("item_id"));
                item.setItemNumber(rs2.getInt("num"));
                items.add(item);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        order.setItems(items);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql= "SELECT * FROM order_info";
        ResultSet rs=dbc.query(sql);
        List<Order> orders=new ArrayList<>();
        try{
            while(rs.next()){
                Order order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setTotal_amount(rs.getDouble("total_amount"));
                order.setCreate_time(rs.getTimestamp("create_time"));
                orders.add(order);
            }
            dbc.close();
            return orders;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public OrderDetail getOrderDetail(int order_id) {
//        DataBaseConnection dbc=new DataBaseConnection();
//        String sql="SELECT * FROM order_detail WHERE order_id="+order_id;
//        ResultSet rs=dbc.query(sql);
//        OrderDetail orderDetail=new OrderDetail();
//        try{
//            while(rs.next()){
//
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
}
