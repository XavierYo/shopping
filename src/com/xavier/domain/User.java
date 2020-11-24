package com.xavier.domain;

public class User {
    private int user_id;
    private String username;
    private String password;
    private String email;

    public int getUserID() {
        return user_id;
    }

    public void setUserID(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() { return email;  }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + "]";
    }



}