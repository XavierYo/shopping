package com.xavier.service;

import com.xavier.domain.Cart;

import java.util.List;

public interface CartService {
    void delCart(int user_id);
    void editCart(List<Cart> carts);
    void insertCart(Cart cart);
    List<Cart> getCartsByID(int user_id);
    List<Cart> getCartsByName(String user_name);
    Cart getCart(int user_id,int item_id);
    void updateCart(int card_id,int number);
    boolean checkCart(List<Cart> carts);//检查库存是否满足
}
