package com.xavier.service;

import com.xavier.domain.User;

import java.sql.SQLException;

public interface UserService{
    boolean regist(User user) throws SQLException;
    User login(String username,String password) throws SQLException;
    int getUserID(String username) throws SQLException;
    String getUserName(int user_id) throws SQLException;
}