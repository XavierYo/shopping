package com.xavier.service;

import com.xavier.domain.User;
public interface UserService{
    boolean regist(User user);
    User login(User user);
    int getUserID(String username);
    String getUserName(int user_id);
}