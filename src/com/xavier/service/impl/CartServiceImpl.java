package com.xavier.service.impl;

import com.xavier.domain.Cart;
import com.xavier.domain.Item;
import com.xavier.service.CartService;
import com.xavier.service.ItemService;
import com.xavier.utils.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    @Override
    public void delCart(int user_id) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="DELETE FROM cart where user_id="+user_id;
        int rs_update=dbc.delete(sql);
        dbc.close();
    }

    @Override
    public void editCart(List<Cart> carts) {

    }

    @Override
    public void insertCart(Cart cart) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="INSERT INTO cart(item_id,user_id,num) VALUES("
                +cart.getItem().getItemID()+","
                +cart.getUser_id()+","
                +cart.getNumber()+")";
        int rs_update=dbc.update(sql);
        dbc.close();
    }

    @Override
    public List<Cart> getCartsByID(int user_id) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM cart WHERE user_id="+user_id;
        ResultSet rs=dbc.query(sql);
        List<Cart> carts = new ArrayList<>();
        try{
            if(rs!=null){
                while(rs.next()){

                    Cart cart=new Cart();
                    cart.setCart_id(rs.getInt("cart_id"));
                    ItemServiceImpl itemService=new ItemServiceImpl();
                    Item item=itemService.getItemByID(rs.getInt("item_id"));
                    cart.setItem(item);
                    cart.setUser_id(rs.getInt("user_id"));
                    cart.setNumber(rs.getInt("num"));
                    carts.add(cart);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        dbc.close();
        return carts;
    }

    @Override
    public List<Cart> getCartsByName(String user_name) {
        UserServiceImpl userService=new UserServiceImpl();
        int user_id=userService.getUserID(user_name);
        return getCartsByID(user_id);
    }

    @Override
    public Cart getCart(int user_id, int item_id) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM cart WHERE user_id="+user_id+" and item_id="+item_id;
        ResultSet rs=dbc.query(sql);
        try{
            if(rs.next()){
                Cart cart=new Cart();
                cart.setCart_id(rs.getInt("cart_id"));
                System.out.println("CartServiceImpl: set cart_id="+rs.getInt("cart_id"));
                ItemServiceImpl itemService=new ItemServiceImpl();
                Item item=itemService.getItemByID(rs.getInt("item_id"));
                cart.setItem(item);
                cart.setUser_id(rs.getInt("user_id"));
                cart.setNumber(rs.getInt("num"));
                System.out.println("CartServiceImpl: return cart that cart_id="+cart.getCart_id());
                return cart;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;//获取失败
    }

    @Override
    public void updateCart(int cart_id, int number) {
        System.out.println("updateCart: cart_id="+cart_id);
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="UPDATE cart SET num="+number+" WHERE cart_id="+cart_id;
        int rs_update=dbc.update(sql);
        dbc.close();
    }

    @Override
    public boolean checkCart(List<Cart> carts) {
        for(Cart cart:carts){
            if(cart.getNumber()>cart.getItem().getItemNumber()){
                return false;
            }
        }
        return true;
    }
}
