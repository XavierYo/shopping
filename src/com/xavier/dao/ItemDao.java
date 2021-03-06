package com.xavier.dao;

import com.xavier.domain.Item;
import com.xavier.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDao {
    // 商品添加
    public boolean addItem(Item item) throws SQLException {
        String sql = "insert into item values(null,?,?,?,?,?)";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return 0!=qr.update(sql,item.getItem_name(),item.getPrice(),item.getCategory_id(),item.getDescription(),item.getNum());
    }

    // 查询所有商品
    public List<Item> getAllItems() throws SQLException{
        String sql = "select * from item";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<>(Item.class));
    }

    // searched by id
    public Item getItemById(int id) throws SQLException {
        String sql = "select * from item where item_id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println(sql);
        return qr.query(sql, new BeanHandler<>(Item.class), id);
    }

    // searched by name
    public Item getItemByName(String name) throws SQLException{
        String sql = "select * from item where item_name=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println(sql);
        return qr.query(sql, new BeanHandler<>(Item.class), name);
    }


    // 编辑item项
    public boolean modItem(Item item) throws SQLException {
        String sql = "update item set item_name=?,price=?,category_id=?,num=?,description=? where item_id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println(sql);
        return 0!=qr.update(sql, item.getItem_name(), item.getPrice(), item.getCategory_id(), item.getNum(),
                item.getDescription(),item.getItem_id());
    }

    // 模糊搜索
    /*field为字段名称、msg为为字段值*/
    public List<Item> simpleSelect(String field, String msg) throws SQLException {
        String sql = "select * from item where " + field + " like ?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println(sql);
        return qr.query(sql, new BeanListHandler<Item>(Item.class), "%" + msg + "%");
    }

    //删除 by id
    public boolean delById(int id) throws SQLException {
        String sql = "delete from item where item_id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println(sql);
        return 0!=qr.update(sql, id);
    }

    // search by category_id
    public List<Item> findByCatID(int category_id) throws SQLException {
        String sql = "select * from item where category_id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println(sql);
        return qr.query(sql, new BeanListHandler<Item>(Item.class), category_id);
    }
    // 获取推荐商品
    public List<Item> getRecommend() throws SQLException{
        String sql1 = "SELECT item_id, COUNT(*) AS count  FROM order_detail  GROUP BY item_id  ORDER BY count DESC  LIMIT 4";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        List<Integer> list = qr.query(sql1, new ColumnListHandler<Integer>("item_id"));
        String sql2 = "SELECT * FROM item WHERE item_id=?";
        List<Item> items = new ArrayList<>();
        for (Integer l:list) {
            items.add(qr.query(sql2,new BeanHandler<Item>(Item.class),l));
        }
        return items;
    }


}
