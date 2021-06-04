package com.xavier.service.impl;

import com.xavier.dao.ItemDao;
import com.xavier.domain.Item;
import com.xavier.service.ItemService;
import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    @Override
    public boolean delByID(int ID) throws SQLException{
        ItemDao itemDao = new ItemDao();
        return itemDao.delById(ID);
    }

    @Override
    public boolean delByName(String name) throws SQLException {
        ItemDao itemDao = new ItemDao();
        Item item = itemDao.getItemByName(name);
        return itemDao.delById(item.getItem_id());
    }

    @Override
    public boolean ins(Item item) throws SQLException{
        ItemDao itemDao = new ItemDao();
        return itemDao.addItem(item);
    }

    @Override
    public boolean modItem(Item newItem) throws SQLException {
        ItemDao itemDao = new ItemDao();
        return itemDao.modItem(newItem);

    }

    @Override
    public Item getItemByID(int ID) throws SQLException{
        ItemDao itemDao = new ItemDao();
        return itemDao.getItemById(ID);
    }


    @Override
    public Item getItemByName(String name) throws SQLException {
        ItemDao itemDao = new ItemDao();
        return itemDao.getItemByName(name);
    }

    @Override
    public List<Item> getItemsByCatID(int ID) throws SQLException {

        ItemDao itemDao = new ItemDao();
        return itemDao.findByCatID(ID);
    }

    @Override
    public List<Item> getItemsByKeyword(String keyword) throws SQLException{
        ItemDao itemDao = new ItemDao();
        return itemDao.simpleSelect("item_name",keyword);
    }

    @Override
    public List<Item> getAllItems() throws SQLException{
        ItemDao itemDao = new ItemDao();
        return itemDao.getAllItems();
    }


}
