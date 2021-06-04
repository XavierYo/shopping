package com.xavier.service.impl;
import java.sql.*;

import com.xavier.dao.UserDao;
import com.xavier.domain.User;
import com.xavier.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public boolean regist(User user) throws SQLException{
        UserDao userDao = new UserDao();
        boolean flag = userDao.regist(user);
        return flag;
    }

    @Override
    public User login(String username,String password) throws SQLException{
        UserDao userDao = new UserDao();
        User user = userDao.login(username,password);
        return user;
    }

    @Override
    public int getUserID(String username) throws SQLException{
        UserDao userDao = new UserDao();
        return userDao.getUserID(username);
    }

    @Override
    public String getUserName(int user_id) throws SQLException{
        UserDao userDao = new UserDao();
        return userDao.getUserName(user_id);
    }
}