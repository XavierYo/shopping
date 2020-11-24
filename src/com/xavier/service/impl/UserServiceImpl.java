package com.xavier.service.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.xavier.domain.Cart;
import com.xavier.domain.Item;
import com.xavier.domain.User;
import com.xavier.service.UserService;
import com.xavier.utils.DataBaseConnection;

public class UserServiceImpl implements UserService {

    @Override
    public boolean regist(User user) {
        //连接数据库
        boolean flag=false;
        DataBaseConnection dbc=new DataBaseConnection();
        String sql1="SELECT * FROM user where username='"+user.getUsername()+"'";

        try{
            ResultSet rs=dbc.query(sql1);
            if(rs.next()){
                dbc.close();
                return false;
            }
            String sql2="INSERT INTO user (username, password, email) VALUES ('"
                    +user.getUsername()+"','"
                    +user.getPassword()+"','"
                    +user.getEmail()+"')";
            if(dbc.update(sql2)>0){
                flag=true;
            }
            dbc.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }



    @Override
    public User login(User user){
        //连接数据库
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM user where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'";
        ResultSet rs=dbc.query(sql);
        try{
            if(rs.next()){
                user.setUserID(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                dbc.close();
                return user;
            }
            else {
                dbc.close();
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getUserID(String username) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM user WHERE username='"+username+"'";
        ResultSet rs=dbc.query(sql);
        try{
            if(rs.next()){
                return rs.getInt("user_id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return -1;//-1即查询失败
    }

    @Override
    public String getUserName(int user_id) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM user WHERE user_id='"+user_id+"'";
        ResultSet rs=dbc.query(sql);
        try{
            if(rs.next()){
                return rs.getString("username");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;//查询失败
    }
}