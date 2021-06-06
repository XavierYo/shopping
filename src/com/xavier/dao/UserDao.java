package com.xavier.dao;

import com.xavier.domain.User;
import com.xavier.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    // 注册
    public boolean regist(User user) throws SQLException {

        String sql = "insert into user (username, password, email) values(?,?,?)";

        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        return qr.update(sql, user.getUsername(),user.getPassword(),user.getEmail())!=0;
    }

    // 登录
    public User login(String username, String password) throws SQLException {
        String sql = "select * from user where username=? and password=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println(sql);
        return qr.query(sql, new BeanHandler<User>(User.class), username,password);

    }

    // 通过用户名查询用户id
    public int getUserID(String username) throws SQLException{
        String sql = "select * from user where username=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        User user=qr.query(sql, new BeanHandler<User>(User.class), username);
        System.out.println(sql);
        return user.getUser_id();
    }
    // 通过id查询用户名
    public String getUserName(int user_id) throws SQLException{
        String sql = "select * from user where user_id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        User user=qr.query(sql, new BeanHandler<User>(User.class), user_id);
        System.out.println(sql);
        return user.getUsername();
    }
    // 通过id获取用户
    public User getUser(int user_id) throws SQLException{
        String sql = "select * from user where user_id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        User user=qr.query(sql, new BeanHandler<User>(User.class), user_id);
        System.out.println(sql);
        return user;
    }
    // 通过username获取用户
    public User getUser(String username) throws SQLException{
        String sql = "select * from user where username=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        User user=qr.query(sql, new BeanHandler<User>(User.class), username);
        System.out.println(sql);
        return user;
    }

    // 修改用户
    public void modUser(User user) throws SQLException{
        String sql = "update user set username=?,password=?,role=?,email=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql,user.getUsername(),user.getPassword(),user.getRole(),user.getEmail());
    }
    // 添加销售员
    public void addSeller(User seller) throws SQLException{
        String sql = "insert into user (username, password,role, email) values(?,?,?,?)";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql, seller.getUsername(),seller.getPassword(),1,seller.getEmail());
    }
    // 删除销售员
    public void delSeller(int user_id) throws SQLException{
        String sql = "delete user where user_id=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql,user_id);
    }

}
