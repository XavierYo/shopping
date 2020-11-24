package com.xavier.service.impl;

import com.xavier.domain.Item;
import com.xavier.service.ItemService;
import com.xavier.utils.DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    @Override
    public boolean delByID(int ID) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="DELETE FROM item WHERE item_id="+ID;
        boolean flag=dbc.delete(sql)>0;
        dbc.close();
        return flag;
    }

    @Override
    public boolean delByName(String name) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="DELETE FROM item WHERE item_name='"+name+"'";
        boolean flag=dbc.delete(sql)>0;
        dbc.close();
        return flag;
    }

    @Override
    public void setNumberByID(int ID, int number) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="UPDATE item SET num="+number+" WHERE item_id="+ID;
        int rs_update=dbc.delete(sql);
        dbc.close();
    }

    @Override
    public void setNumberByName(String name, int number) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="UPDATE item SET num="+number+" WHERE item_name='"+name+"'";
        int rs_update=dbc.delete(sql);
        dbc.close();
    }

    @Override
    public boolean ins(Item item) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="INSERT INTO item(item_name,price,category_id,num,description) VALUES('"
                +item.getItemName()+"',"
                +item.getPrice()+","
                +item.getCategoryID()+","
                +item.getItemNumber()+",'"
                +item.getItemDesc()+"')";
        boolean flag=dbc.update(sql)>0;
        dbc.close();
        return flag;
    }

    @Override
    public boolean modItem(int item_id, Item newItem) {
        DataBaseConnection dbc = new DataBaseConnection();
        String sql="UPDATE item SET item_name='"
                +newItem.getItemName()+"',price="
                +newItem.getPrice()+",category_id="
                +newItem.getCategoryID()+",num="
                +newItem.getItemNumber()+",description='"
                +newItem.getItemDesc()+"' WHERE item_id="
                +item_id;
        boolean flag=dbc.update(sql)>0;
        dbc.close();
        return flag;
    }

    @Override
    public Item getItemByID(int ID) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM item WHERE item_id="+ID;
        ResultSet rs=dbc.query(sql);
        try{
            if(rs!=null){
                while(rs.next()){
                    return readAItem(rs);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        dbc.close();
        return null;
    }

    @Override
    public int getItemIDByName(String name) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM item WHERE item_name='"+name+"'";
        ResultSet rs=dbc.query(sql);
        try{
            if(rs.next()){
                return rs.getInt("item_id");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;//查询失败
    }

    @Override
    public Item getItemByName(String name) {
        int item_id=getItemIDByName(name);
        return getItemByID(item_id);
    }

    @Override
    public List<Item> getItemsByCatID(int ID) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM item WHERE category_id="+ID;
        ResultSet rs=dbc.query(sql);
        List<Item> items = new ArrayList<>();
        try{
            if(rs!=null){
                while(rs.next()){
                    items.add(readAItem(rs));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        dbc.close();
        return items;
    }

    @Override
    public List<Item> getItemsByKeyword(String keyword) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM item where item_name like '%"+keyword+"%'";
        ResultSet rs=dbc.query(sql);
        List<Item> items = new ArrayList<>();
        try{
            if(rs!=null){
                while(rs.next()){
                    items.add(readAItem(rs));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        dbc.close();
        return items;
    }

    @Override
    public List<Item> getAllItems() {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM item";
        ResultSet rs=dbc.query(sql);
        List<Item> items = new ArrayList<>();
        try{
            if(rs!=null){
                while(rs.next()){
                    items.add(readAItem(rs));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        dbc.close();
        return items;
    }

    @Override
    public Item readAItem(ResultSet rs) {
        try{
            if(rs!=null){
                Item item=new Item();
                item.setItemID(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setPrice(rs.getDouble("price"));
                item.setCategoryID(rs.getInt("category_id"));
                item.setItemDesc(rs.getString("description"));
                item.setItemNumber(rs.getInt("num"));
                return item;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
