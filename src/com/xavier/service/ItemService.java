package com.xavier.service;

import com.xavier.domain.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ItemService {
    boolean delByID(int ID) throws SQLException;
    boolean delByName(String name) throws SQLException;
    boolean ins(Item item) throws SQLException;
    boolean modItem(Item newItem) throws SQLException;
    Item getItemByID(int ID) throws SQLException;
    Item getItemByName(String name) throws SQLException;
    List<Item> getItemsByCatID(int ID) throws SQLException;
    List<Item> getItemsByKeyword(String keyword) throws SQLException;
    List<Item> getAllItems() throws SQLException;

}

