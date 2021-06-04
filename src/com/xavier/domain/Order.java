package com.xavier.domain;


import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int order_id;
    private double total_amount;
    private Timestamp create_time;
    private int user_id;

    public int getOrder_id(){return order_id;}
    public void setOrder_id(int order_id){this.order_id=order_id;}

    public double getTotal_amount(){return total_amount;}
    public void setTotal_amount(double total_amount){this.total_amount=total_amount;}

    public int getUser_id(){return user_id;}
    public void setUser_id(int user_id){this.user_id=user_id;}

    public Timestamp getCreate_time(){return create_time;}
    public void setCreate_time(Timestamp create_time){this.create_time=create_time;}

}
