package com.xavier.service;

import com.xavier.domain.Item;

import java.sql.ResultSet;
import java.util.List;

public interface ItemService {
    int getItemIDByName(String name);
    boolean delByID(int ID);
    boolean delByName(String name);
    void setNumberByID(int ID,int number);
    void setNumberByName(String name,int number);
    boolean ins(Item item);
    boolean modItem(int item_id,Item newItem);
    Item getItemByID(int ID);
    Item getItemByName(String name);
    List<Item> getItemsByCatID(int ID);
    List<Item> getItemsByKeyword(String keyword);
    List<Item> getAllItems();
    Item readAItem(ResultSet rs);
}

