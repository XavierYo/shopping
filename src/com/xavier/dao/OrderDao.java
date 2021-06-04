package com.xavier.dao;

import com.xavier.domain.Item;
import com.xavier.domain.Order;
import com.xavier.domain.OrderDetail;
import com.xavier.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderDao {
    // 添加订单,返回订单id,返回类型可能需要改为long
    public int addOrder(Order order, Map<Item,Integer> cart) throws SQLException {
        String sql1="insert into order_info (total_amount,user_id) values(?,?)";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        int order_id = ((BigInteger)qr.insert(sql1,new ScalarHandler<>(),order.getTotal_amount(),order.getUser_id())).intValue();
        // map遍历
        Set<Map.Entry<Item,Integer>> entries = cart.entrySet();
        String sql2="insert into order_detail(order_id,item_id,num) values(?,?,?)";
        String sql3="update item set num=? where item_id=?";
        for (Map.Entry<Item,Integer> entry : entries){
            qr.update(sql2,order_id,entry.getKey().getItem_id(),entry.getValue());
            qr.update(sql3,entry.getKey().getNum()-entry.getValue(),entry.getKey().getItem_id());
        }
        System.out.println(sql1);
        System.out.println(sql2);
        return order_id;
    }

    // 通过用户id查订单
    public List<Order> getOrders(int user_id) throws SQLException{
        String sql="select * from order_info where user_id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println(sql);
        return qr.query(sql,new BeanListHandler<Order>(Order.class),user_id);
    }

    // 通过订单id查订单
    public  Order getOrder(int order_id) throws SQLException{
        String sql = "select * from order_info where order_id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println(sql);
        return qr.query(sql,new BeanHandler<Order>(Order.class),order_id);
    }

    // 获取所有订单
    public List<Order> getAllOrders() throws SQLException{
        String sql="select * from order_info";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println(sql);
        return qr.query(sql,new BeanListHandler<Order>(Order.class));
    }

    // 获取订单详情
    public Map<Item,Integer> getOrderDetail(int order_id) throws SQLException{
        String sql="select * from order_detail where order_id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        List<OrderDetail> od = qr.query(sql,new BeanListHandler<OrderDetail>(OrderDetail.class),order_id);
        Map<Item,Integer> cart = new HashMap<Item,Integer>();
        ItemDao itemDao = new ItemDao();
        for(int i=0;i<od.size();i++){
            int item_id=od.get(i).getItem_id();
            int num = od.get(i).getNum();
            Item item = itemDao.getItemById(item_id);
            cart.put(item,num);
        }
        System.out.println(sql);
        return cart;
    }
}
