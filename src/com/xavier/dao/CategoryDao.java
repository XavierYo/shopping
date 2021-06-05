package com.xavier.dao;

import com.xavier.domain.Category;
import com.xavier.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
    public String getCategoryName(int id) throws SQLException {
        String sql="select * from category where category_id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanHandler<>(Category.class), id).getCategory_name();
    }
    public int getCategoryID(String name) throws SQLException {
        String sql="select * from category where category_name=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        Category category = qr.query(sql, new BeanHandler<>(Category.class), name);
        if(category!=null)
            return category.getCategory_id();
        else return -1;
    }
    public boolean insCategory(String name,int seller) throws SQLException{
        String sql = "insert into category (category_name,seller) values(?,?)";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return 0!=qr.update(sql,name,seller);
    }

    public List<Category> getAllCategory() throws SQLException{
        String sql = "select * from category";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<Category>(Category.class));
    }

    public Category getCategory(String name) throws SQLException{
        String sql = "select * from category where category_name=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanHandler<>(Category.class), name);
    }

    public Category getCategory(int id) throws SQLException{
        String sql = "select * from category where category_id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanHandler<>(Category.class), id);
    }

    public List<Category> getCategoriesBySeller(int seller_id) throws SQLException{
        String sql = "select * from category where seller=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<Category>(Category.class),seller_id);
    }
}
